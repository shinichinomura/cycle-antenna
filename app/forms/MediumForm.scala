package forms

import play.api.data._
import play.api.data.Forms._
import models.Medium

case class MediumFormData(name: String, url: String)

class MediumForm

object MediumForm {
  def apply(): Form[MediumFormData] = {
    Form(
      mapping(
        "name" -> nonEmptyText,
        "url" -> text
      )(MediumFormData.apply)(MediumFormData.unapply))
  }

  def apply(id: Long): Form[MediumFormData] = {
    val medium = Medium.findById(id).get

    val form:Form[MediumFormData] = this.apply().fill(MediumFormData(medium.name, medium.url))

    return form
  }
}
