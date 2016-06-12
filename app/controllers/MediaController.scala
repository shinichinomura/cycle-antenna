package controllers

import play.api.mvc._
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import scalikejdbc._
import models.Medium
import forms.MediumForm

class MediaController extends Controller {
  implicit val session = AutoSession

  def index = Action { implicit request =>
    val media: List[Medium] = 
      sql"SELECT * FROM media".map(rs => Medium(rs)).list.apply()

    Ok(views.html.Media.index(media))
  }

  def build = Action {
    val mediumForm = MediumForm()

    Ok(views.html.Media.build(mediumForm))
  }

  def create = Action { implicit request =>
    val mediumForm = MediumForm()

    mediumForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.Media.build(formWithErrors))
      },
      mediumFormData => {
        Medium(mediumFormData.name, mediumFormData.url)
        Redirect(routes.MediaController.index).flashing("success" -> "Medium saved!")
      }
    )
  }
}