package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import repositories.MediumArticleRepository

@Singleton
class HomeController @Inject() extends Controller {
  def index = Action {
    val medium_articles = MediumArticleRepository.fetchRecentlyPublished()

    Ok(views.html.index(medium_articles))
  }
}
