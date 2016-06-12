package models

import scalikejdbc._

case class Medium (id: Long, name: String, url: String)

object Medium {
  implicit val session = AutoSession

  def apply(rs: WrappedResultSet) = new Medium(rs.long("id"), rs.string("name"), rs.string("url"))

  def apply(name: String, url: String): Int = {
    sql"INSERT INTO media (name, url) VALUES (${name}, ${url})".update.apply()
  }
}
