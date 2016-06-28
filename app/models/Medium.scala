package models

import org.joda.time.DateTime
import repositories.MediumRepository

class Medium (val id: Long, val name: String, val url: String, val feed_url: String, val retrieved_at: DateTime) {
  def updateRetrievedAt() = {
    MediumRepository.updateRetrievedAt(id, new DateTime())
  }
}
