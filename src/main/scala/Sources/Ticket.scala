package Sources

import java.time.OffsetDateTime

import io.circe.{Decoder, Encoder}

import OffsetDateTimeParsing._

case class Ticket(
  _id: String,
  url: String,
  external_id: String,
  created_at: OffsetDateTime,
  ticket_type: Option[String],
  subject: String,
  description: Option[String],
  priority: String,
  status: String,
  submitter_id: Int,
  assignee_id: Option[Int],
  organization_id: Option[Int],
  tags: Seq[String],
  has_incidents: Boolean,
  due_at: Option[OffsetDateTime],
  via: String
)

object Ticket {
  implicit val decodeUser: Decoder[Ticket] =
    Decoder.forProduct16(
      "_id",
      "url",
      "external_id",
      "created_at",
      "type",
      "subject",
      "description",
      "priority",
      "status",
      "submitter_id",
      "assignee_id",
      "organization_id",
      "tags",
      "has_incidents",
      "due_at",
      "via"
    )(Ticket.apply)
  // decodeUser: io.circe.Decoder[User] = io.circe.ProductDecoders$$anon$3@1d97297d

  implicit val encodeUser: Encoder[Ticket] =
    Encoder.forProduct16(
      "_id",
      "url",
      "external_id",
      "created_at",
      "type",
      "subject",
      "description",
      "priority",
      "status",
      "submitter_id",
      "assignee_id",
      "organization_id",
      "tags",
      "has_incidents",
      "due_at",
      "via"
    )(t => (
      t._id,
      t.url,
      t.external_id,
      t.created_at,
      t.ticket_type,
      t.subject,
      t.description,
      t.priority,
      t.status,
      t.submitter_id,
      t.assignee_id,
      t.organization_id,
      t.tags,
      t.has_incidents,
      t.due_at,
      t.via
    ))
}