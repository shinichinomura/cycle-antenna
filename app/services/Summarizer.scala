package services

import play.Play
import play.Logger
import dispatch._
import dispatch.Defaults._
import org.json4s._
import org.json4s.jackson.JsonMethods._
import java.net.URL

class Summarizer {
  def summarize(text: String, sent_limit: Int = 3): Option[String] = {
    val base_uri = Play.application.configuration.getString("summpy.url")

    val request = url(base_uri).POST << Map("sent_limit" -> sent_limit.toString, "text" -> text)
    request.setHeader("Content-Type", "text/plain; charset=utf-8")

    val response = Http(request OK as.String)
    val json_string = response()
    val json = parse(json_string)

    Logger.info(json_string)

    val json_map = json.asInstanceOf[JObject].values

    val regexp_head = """^\p{javaWhitespace}+""".r
    val regexp_tail = """\p{javaWhitespace}+$""".r

    json_map.get("summary").get match {
      case sentences:List[String] => {
        Some(sentences.map( s => regexp_tail.replaceAllIn(regexp_head.replaceAllIn(s, ""), "") ).mkString("", "\n", ""))
      }
      case _ => None
    }
  }
}
