import play.api.mvc._
import play.api.test._
import scala.concurrent.Future
import controllers.MediaController

object MediaControllerSpec extends PlaySpecification with Results {
  "MediaController#index" should {
    "should be success" in {
      scalikejdbc.config.DBsWithEnv("test").setupAll

      val controller = new MediaController()
      val result: Future[Result] = controller.index().apply(FakeRequest())

      status(result) must equalTo(OK)
    }
  }
}
