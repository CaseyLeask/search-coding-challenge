package Sources

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

import io.circe.{Decoder, Encoder}

import cats.implicits._

object OffsetDateTimeParsing {
  private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss ZZZZZ")

  implicit val encodeOffsetDateTime: Encoder[OffsetDateTime] = Encoder.encodeString.contramap[OffsetDateTime](_.format(formatter))

  implicit val decodeOffsetDateTime: Decoder[OffsetDateTime] = Decoder.decodeString.emap {
    str => {
      Either.catchNonFatal(OffsetDateTime.parse(str, formatter)).leftMap(e => e.getMessage)
    }
  }

}