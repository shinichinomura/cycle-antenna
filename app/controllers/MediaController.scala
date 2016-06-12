package controllers

import play.api.mvc._
import scalikejdbc._
import models.Medium

class MediaController extends Controller {
  implicit val session = AutoSession

  def index = Action {
    val media: List[Medium] = 
      sql"SELECT * FROM media".map(rs => Medium(rs)).list.apply()

    Ok(views.html.Media.index(media))
  }
}
