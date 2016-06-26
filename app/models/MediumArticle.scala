package models

import collection.JavaConversions._
import org.joda.time.DateTime
import org.jsoup._
import repositories.MediumRepository
import repositories.MediumArticleRepository

case class MediumArticle (url: String, medium_id: Long, title: String, description: String, published_at: DateTime, retrieved_at: DateTime, image_original_url: String) {
  def medium: Medium = {
    MediumRepository.findById(medium_id).get
  }

  def loadOrGetImageOriginalUrl: Option[String] = {
    image_original_url match {
      case _:String => Some(image_original_url)
      case _ => getImageOriginalUrl
    }
  }

  def getImageOriginalUrl: Option[String] = {
    val document = Jsoup.connect(url).userAgent("Mozilla").get

    document.select("meta[property='og:image']").headOption match {
      case Some(e) => {
        storeImageOriginalUrl(e.attr("content"))
        Some(e.attr("content"))
      }
      case _ => None
    }
  }

  private def storeImageOriginalUrl(image_original_url: String): Int = {
    MediumArticleRepository.storeImageOriginalUrl(url, image_original_url)
  }
}
