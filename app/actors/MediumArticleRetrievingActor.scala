package actors

import akka.actor._
import models.Medium
import services.MediumArticleRetriever
import services.MediumArticleRetrievingScheduler

class MediumArticleRetrievingActor extends Actor {
  def receive = {
    case message: String => {
      var scheduler = new MediumArticleRetrievingScheduler()

      scheduler.next() match {
        case Some(medium) => {
          val retriever = new MediumArticleRetriever(medium)
          retriever.retrieve()
        }
        case None => {
          println("フィードを取得すべきメディアがありません。")
        }
      }
    }
  }
}
