package views.helpers

class HTMLHelper

object HTMLHelper {
  def stripTags(string: String): String = {
    val regexp = """<[^>]+>""".r

    regexp.replaceAllIn(string, "")
  }

  def truncate(string: String, length: Int, tail: String = "..."): String = {
    if (string.size > length) {
      string.substring(0, length) + tail
    }
    else {
      string
    }
  }
}
