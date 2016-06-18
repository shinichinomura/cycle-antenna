import java.net.URL
import java.util.Locale
import scala.xml._
import scala.io.Source.fromURL

class RssReader(url: URL) {
  val rss = XML.load(url)

  def items(): Seq[Map[Symbol, String]] = {
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
