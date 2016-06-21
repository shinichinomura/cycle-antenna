package views.helpers

class HTMLHelper

object HTMLHelper {
  def stripTags(string: String): String = {
    val regexp = """<[^>]+>""".r

    regexp.replaceAllIn(string, "")
  }
}
