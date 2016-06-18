package repositories

import scalikejdbc._
import models.MediumArticle

class MediumArticleRepository

object MediumArticleRepository {
  implicit val session = AutoSession

  def fetchByUrl(url: String): Option[MediumArticle] = {
    sql"SELECT * FROM medium_articles WHERE url = ${url}".map{
      rs => new MediumArticle(rs.string("url"), rs.long("medium_id"), rs.string("title"), rs.string("description"), rs.jodaDateTime("published_at"), rs.jodaDateTime("retrieved_at"))
    }.single.apply()
  }

  def register(url: String, medium_id: Long, title: String, description: String, published_at: String, retrieved_at: String): Int = {
    fetchByUrl(url) match {
      case Some(article) =>
        0
      case None =>
        sql"INSERT INTO medium_articles (url, medium_id, title, description, published_at, retrieved_at) VALUES (${url}, ${medium_id}, ${title}, ${description}, ${published_at}, ${retrieved_at})".update.apply()
    }
  }
}