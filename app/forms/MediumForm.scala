package forms

import play.api.data._
import play.api.data.Forms._
import repositories.MediumRepository

case class MediumFormData(name: String, url: String, feed_url: String)

class MediumForm

object MediumForm {
  def apply(): Form[MediumFormData] = {
    Form(
      mapping(
        "name" -> nonEmptyText,
        "url" -> text,
        "feed_url" -> text
      )(MediumFormData.apply)(MediumFormData.unapply))
  }

  def apply(id: Long): Form[MediumFormData] = {
    val medium = MediumRepository.findById(id).get

    val form:Form[MediumFormData] = this.apply().fill(MediumFormData(medium.name, medium.url, medium.feed_url))

    return form
  }
}
