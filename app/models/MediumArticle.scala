package models

import org.joda.time.DateTime

case class MediumArticle (url: String, medium_id: Long, title: String, description: String, published_at: DateTime, retrieved_at: DateTime)

