package models

import scalikejdbc._

case class Medium (id: Long, name: String, url: String)

object Medium {
  implicit val session = AutoSession

  def apply(rs: WrappedResultSet) = new Medium(rs.long("id"), rs.string("name"), rs.string("url"))

  def findById(id: Long): Option[Medium] = {
    sql"SELECT * FROM media WHERE id = ${id}".map{ rs => Medium(rs) }.single.apply()
  }

  def apply(name: String, url: String): Int = {
    sql"INSERT INTO media (name, url) VALUES (${name}, ${url})".update.apply()
  }

  def update(id: Long, name: String, url: String): Int = {
    sql"UPDATE media SET name = ${name}, url = ${url} WHERE id = ${id}".update.apply()
  }
}
