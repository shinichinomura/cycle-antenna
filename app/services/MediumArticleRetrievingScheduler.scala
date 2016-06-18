package services

import models.Medium
import repositories.MediumRepository

class MediumArticleRetrievingScheduler {
  def next(): Option[Medium] = {
    MediumRepository.fetchNextRetrieving()
  }
}
