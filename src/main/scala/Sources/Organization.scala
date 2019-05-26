package Sources

import java.time.OffsetDateTime

case class Organization(
  _id: Int,
  url: String,
  external_id: String,
  name: String,
  domain_names: Seq[String],
  created_at: OffsetDateTime,
  details: String,
  shared_tickets: Boolean,
  tags: Seq[String]
)
