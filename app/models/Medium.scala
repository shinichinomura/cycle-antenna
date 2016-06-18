package models

import org.joda.time.DateTime
import repositories.MediumRepository

class Medium (val id: Long, val name: String, val url: String, val feed_url: String) {
  def updateRetrievedAt() = {
    MediumRepository.updateRetrievedAt(id, new DateTime())
  }
}
