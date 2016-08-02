package models.medium_article_content_retrievers

class MediumArticleContentRetrieverFactory

object MediumArticleContentRetrieverFactory {
  def create(url: String): MediumArticleContentRetriever = {
    url match {
      case "http://cyclist.sanspo.com/" => {
        new CyclistContentRetriever()
      }
      case "http://www.roadbike.jp/" => {
        new RoadbikeLoveContentRetriever()
      }
      case "http://jitensha-hoken.jp/blog/" => {
        new FrameContentRetriever()
      }
      case "http://www.cyclesports.jp/" => {
        new CyclesportsContentRetriever()
      }
      case "http://www.cyclowired.jp/" => {
        new CyclowiredContentRetriever()
      }
      case "http://rbs.ta36.com/" => {
        new ITEngineerRoadbikeBlogContentRetriever()
      }
      case "http://ennori.jp/bike" => {
        new EnnoriContentRetriever()
      }
    }
  }
}
