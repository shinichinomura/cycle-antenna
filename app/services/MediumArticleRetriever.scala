package services

import java.net.URL
import java.util.Locale
import org.joda.time.DateTime
import org.joda.time.format._
import models.Medium
import repositories.MediumArticleRepository
import utils._

class MediumArticleRetriever(medium: Medium) {
  val rss_reader = new RssReader(medium.feed_url)

  def retrieve() = {
    val current_datetime = new DateTime()

    medium.updateRetrievedAt()

    rss_reader.items.foreach { item =>
      MediumArticleRepository.register(
        item.get('link).get,
        medium.id,
        item.get('title).get,
        item.get('description).get,
        new DateTimeConverter(item.get('pubDate).get).toMySQLString(),
        DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").print(current_datetime)
      )
    }
  }
}
