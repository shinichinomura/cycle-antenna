package models.medium_article_content_retrievers

abstract class MediumArticleContentRetriever {
  val html_tag_regexp = """<[^>]+>""".r

  def retrieve(url: String): Option[String]
}
