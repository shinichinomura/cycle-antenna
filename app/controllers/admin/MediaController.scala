package controllers.admin

import play.api.mvc._
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import scalikejdbc._
import models.Medium
import repositories.MediumRepository
import repositories.MediumArticleRepository
import forms.MediumForm

class MediaController extends Controller {
  implicit val session = AutoSession

  def index = Action { implicit request =>
    val media = MediumRepository.fetchAll()

    Ok(views.html.admin.Media.index(media, MediumArticleRepository.fetchRecentlyPublished()))
  }

  def build = Action {
    val mediumForm = MediumForm()

    Ok(views.html.admin.Media.build(mediumForm))
  }

  def create = Action { implicit request =>
    val mediumForm = MediumForm()

    mediumForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.admin.Media.build(formWithErrors))
      },
      mediumFormData => {
        MediumRepository.register(mediumFormData.name, mediumFormData.url, mediumFormData.feed_url)
        Redirect(routes.MediaController.index).flashing("success" -> "Medium saved!")
      }
    )
  }

  def edit(id: Long) = Action {
    val mediumForm = MediumForm(id)

    Ok(views.html.admin.Media.edit(id, mediumForm))
  }

  def update(id: Long) = Action { implicit request =>
    val mediumForm = MediumForm()

    mediumForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.admin.Media.edit(id, formWithErrors))
      },
      mediumFormData => {
        MediumRepository.update(id, mediumFormData.name, mediumFormData.url, mediumFormData.feed_url)
        Redirect(routes.MediaController.index).flashing("success" -> "Medium updated!")
      }
    )
  }

  def delete(id: Long) = Action { implicit request =>
    MediumRepository.delete(id)

    Redirect(routes.MediaController.index).flashing("success" -> "Medium deleted!")
  }
}
