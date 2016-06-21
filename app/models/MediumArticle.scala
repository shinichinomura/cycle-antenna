package models

import org.joda.time.DateTime
import repositories.MediumRepository

case class MediumArticle (url: String, medium_id: Long, title: String, description: String, published_at: DateTime, retrieved_at: DateTime) {
  def medium: Medium = {
    MediumRepository.findById(medium_id).get
  }
}

