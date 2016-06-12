package forms

import play.api.data._
import play.api.data.Forms._

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
}
