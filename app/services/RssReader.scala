package services

import scala.xml._
import dispatch._
import dispatch.Defaults._

class RssReader(val feed_url: String) {
  def items(): Seq[Map[Symbol, String]] = {
    val request = url(feed_url)
    val response = Http(request OK as.String)
    val rss_string = response()

    val clean_rss_string = rss_string.replace("\u0000", "")

    val rss = XML.loadString(clean_rss_string)

    (rss \\ "item").map { item =>
      Map(
        'title       -> (item \ "title").head.text,
        'link        -> (item \ "link").head.text,
        'pubDate     -> (item \ "pubDate").head.text,
        'description -> (item \ "description").head.text
      )  
    }
  }
}
