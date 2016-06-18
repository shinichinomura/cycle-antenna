package services

import scala.xml._
import dispatch._
import dispatch.Defaults._

class RssReader(val feed_url: String) {
  def items(): Seq[Map[Symbol, String]] = {
    val request = url(feed_url)
    val response = Http(request OK as.String)
    val rss = XML.loadString(response())

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
