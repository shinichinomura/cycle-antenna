import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension
import play.api._
import akka.actor._
import actors.MediumArticleRetrievingActor

object Global extends GlobalSettings {
  val system = ActorSystem("MediumArticleRetrievingSystem")
  val actor = system.actorOf(Props(classOf[MediumArticleRetrievingActor]))

  override def onStart(app: Application) = {
    QuartzSchedulerExtension(system).schedule("Every1Minutes", actor, "run")
  }

  override def onStop(app: Application) = {
    system.shutdown()
  }
}
