package models

import collection.JavaConversions._
import dispatch._
import dispatch.Defaults._
import org.joda.time.DateTime
import org.jsoup._
import play.Logger
import repositories.MediumRepository
import repositories.MediumArticleRepository
import services.Summarizer

case class MediumArticle (url: String, medium_id: Long, title: String, description: String, summary: String, published_at: DateTime, retrieved_at: DateTime, image_original_url: String) {
  def medium: Medium = {
    MediumRepository.findById(medium_id).get
  }

  def loadOrGetImageOriginalUrl: Option[String] = {
    image_original_url match {
      case _:String => Some(image_original_url)
      case _ => getImageOriginalUrl
    }
  }

  def loadOrCreateSummary: String = {
    summary match {
      case _:String => summary
      case _ => {
        createSummary match {
          case Some(string) => string
          case _ => ""
        }
      }
    }
  }

  private def getImageOriginalUrl: Option[String] = {
    val document = Jsoup.connect(url).userAgent("Mozilla").get

    document.select("meta[property='og:image']").headOption match {
      case Some(e) => {
        storeImageOriginalUrl(e.attr("content"))
        Some(e.attr("content"))
      }
      case _ => None
    }
  }

  private def createSummary: Option[String] = {
    val summarizer = new Summarizer()

    retrieveContent match {
      case Some(s) => {
        summarizer.summarize(s) match {
          case Some(summary) => {
            storeSummary(summary)
            Some(summary)
          }
          case _ => None
        }
      }
      case _ => {
        None
      }
    }
  }

  private def storeImageOriginalUrl(image_original_url: String): Int = {
    MediumArticleRepository.storeImageOriginalUrl(url, image_original_url)
  }

  private def storeSummary(summary: String): Int = {
    MediumArticleRepository.storeSummary(url, summary)
  }

  private def retrieveContent: Option[String] = {
    val retriever = medium_article_content_retrievers.MediumArticleContentRetrieverFactory.create(medium.url)
    retriever.retrieve(url)
  }
}
