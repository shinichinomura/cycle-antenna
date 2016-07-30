package models.medium_article_content_retrievers

import collection.JavaConversions._
import org.jsoup._
import play.Logger

class EnnoriContentRetriever extends MediumArticleContentRetriever {
  def retrieve(url: String): Option[String] = {
    val document = Jsoup.connect(url).userAgent("Mozilla").get

    document.select("div.article-body").headOption match {
      case Some(e) => {
        Logger.info(html_tag_regexp.replaceAllIn(e.outerHtml, ""))
        Some(html_tag_regexp.replaceAllIn(e.outerHtml, ""))
      }
      case _ => None
    }
  }
}
