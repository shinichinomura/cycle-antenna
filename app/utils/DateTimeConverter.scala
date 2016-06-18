package utils

import java.util.Locale
import org.joda.time.DateTime
import org.joda.time.format._
import org.joda.time.DateTimeZone

class DateTimeConverter(val datetime: DateTime) {
  def this(datetime_string: String) = {
    this((DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss Z").withLocale(Locale.ENGLISH).withOffsetParsed()).parseDateTime(datetime_string))
  }

  def toMySQLString(): String = {
    DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").print(datetime.toDateTime(DateTimeZone.getDefault()))
  }
}
