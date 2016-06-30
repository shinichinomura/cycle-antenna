package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import repositories.MediumArticleRepository

@Singleton
class HomeController @Inject() extends Controller {
  def index(page: Long) = Action {
    val medium_articles = MediumArticleRepository.fetchRecentlyPublished((page - 1)*20, 20)

    Ok(views.html.index(medium_articles, page))
  }
}
