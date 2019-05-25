package search

import java.time.OffsetDateTime

case class User(
  _id: Int,
  url: String,
  external_id: String,
  name: String,
  alias: Option[String],
  created_at: OffsetDateTime,
  active: Boolean,
  verified: Option[Boolean],
  shared: Boolean,
  locale: Option[String],
  timezone: Option[String],
  last_login_at: OffsetDateTime,
  email: Option[String],
  phone: String,
  signature: String,
  organization_id: Option[Int],
  tags: Seq[String],
  suspended: Boolean,
  role: String
)
