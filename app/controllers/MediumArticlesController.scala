package controllers

import play.api.mvc._
import repositories.MediumArticleRepository

class MediumArticlesController extends Controller {
  def show(url: String) = Action { implicit request =>
    val medium_article = MediumArticleRepository.fetchByUrl(url).get

    Ok(views.html.MediumArticle.show(medium_article))
  }
}
