package repositories

import scalikejdbc._
import org.joda.time.DateTime
import models.Medium
import utils._

class MediumRepository

object MediumRepository {
  implicit val session = AutoSession

  def fetchAll(): List[Medium] = {
    sql"SELECT * FROM media".map{
      rs => new Medium(rs.long("id"), rs.string("name"), rs.string("url"), rs.string("feed_url"))
    }.list.apply()
  }

  def findById(id: Long): Option[Medium] = {
    sql"SELECT * FROM media WHERE id = ${id}".map{
      rs => new Medium(rs.long("id"), rs.string("name"), rs.string("url"), rs.string("feed_url"))
    }.single.apply()
  }

  def fetchNextRetrieving(): Option[Medium] = {
    sql"SELECT * FROM media WHERE url IS NOT NULL ORDER BY retrieved_at LIMIT 1".map{
      rs => new Medium(rs.long("id"), rs.string("name"), rs.string("url"), rs.string("feed_url"))
    }.single.apply()
  }

  def register(name: String, url: String, feed_url: String): Int = {
    sql"INSERT INTO media (name, url, feed_url) VALUES (${name}, ${url}, ${feed_url})".update.apply()
  }

  def update(id: Long, name: String, url: String, feed_url: String): Int = {
    sql"UPDATE media SET name = ${name}, url = ${url}, feed_url = ${feed_url} WHERE id = ${id}".update.apply()
  }

  def updateRetrievedAt(id: Long, retrieved_at: DateTime): Int = {
    val retrieved_at_string = (new DateTimeConverter(retrieved_at)).toMySQLString()

    sql"UPDATE media SET retrieved_at = ${retrieved_at_string} WHERE id = ${id}".update.apply()
  }

  def delete(id: Long): Int = {
    sql"DELETE FROM media WHERE id = ${id}".update.apply()
  }
}
