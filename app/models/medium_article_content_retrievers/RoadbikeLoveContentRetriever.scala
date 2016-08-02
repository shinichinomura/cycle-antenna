package models.medium_article_content_retrievers

import collection.JavaConversions._
import org.jsoup._
import play.Logger

class RoadbikeLoveContentRetriever extends MediumArticleContentRetriever {
  def retrieve(url: String): Option[String] = {
    val document = Jsoup.connect(url).userAgent("Mozilla").get

    document.select("div.entry-content p") match {
      case elements if !elements.isEmpty() =>{
        Some(html_tag_regexp.replaceAllIn(elements.map(_.outerHtml).mkString("", "", ""), ""))
      }
      case _ => None
    }
  }
}
