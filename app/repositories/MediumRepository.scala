package repositories

import scalikejdbc._
import models.Medium

class MediumRepository

object MediumRepository {
  implicit val session = AutoSession

  def fetchAll(): List[Medium] = {
    sql"SELECT * FROM media".map{
      rs => new Medium(rs.long("id"), rs.string("name"), rs.string("url"))
    }.list.apply()
  }

  def findById(id: Long): Option[Medium] = {
    sql"SELECT * FROM media WHERE id = ${id}".map{
      rs => new Medium(rs.long("id"), rs.string("name"), rs.string("url"))
    }.single.apply()
  }

  def register(name: String, url: String): Int = {
    sql"INSERT INTO media (name, url) VALUES (${name}, ${url})".update.apply()
  }

  def update(id: Long, name: String, url: String): Int = {
    sql"UPDATE media SET name = ${name}, url = ${url} WHERE id = ${id}".update.apply()
  }

  def delete(id: Long): Int = {
    sql"DELETE FROM media WHERE id = ${id}".update.apply()
  }
}
