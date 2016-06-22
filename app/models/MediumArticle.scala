package models

import collection.JavaConversions._
import org.joda.time.DateTime
import org.jsoup._
import repositories.MediumRepository

case class MediumArticle (url: String, medium_id: Long, title: String, description: String, published_at: DateTime, retrieved_at: DateTime) {
  def medium: Medium = {
    MediumRepository.findById(medium_id).get
  }

  def image_original_url: Option[String] = {
    val document = Jsoup.connect(url).userAgent("Mozilla").get

    document.select("meta[property='og:image']").headOption match {
      case Some(e) => Some(e.attr("content"))
      case _ => None
    }
  }
}
