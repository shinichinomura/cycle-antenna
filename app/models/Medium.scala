package models

import scalikejdbc._

case class Medium (id: Long, name: String, url: String)

object Medium {
  def apply(rs: WrappedResultSet) = new Medium(rs.long("id"), rs.string("name"), rs.string("url"))
}
