package Sources

import java.time.OffsetDateTime

import io.circe._
import io.circe.generic.auto._
import io.circe.parser._

import Sources.OffsetDateTimeParsing._

class UserDecoderTest extends org.specs2.mutable.Specification {
  "Decoding User" >> {
    "with an invalid JSON string" >> {
      val rawJson: String =
        """
        {
          "_id": 1,
          "url": "http://initech.zendesk.com/api/v2/users/1.json",
          "external_id": "74341f74-9c79-49d5-9611-87ef9b6eb75f",
          "name": "Francisca Rasmussen",
          "created_at": "2016-04-15T05:19:46 -10:00",
          "active": true,
          "shared": false,
          "last_login_at": "2013-08-04T01:03:27 -10:00",
          "phone": "8335-422-718",
          "signature": "Don't Worry Be Happy!",
          "tags": [],
          "suspended": true,
          "role": "admin",
        }
      """

      "should not correctly parse" >> {
        val result: Either[Error, User] = decode[User](rawJson)

        result must beLeft
      }
    }

    "with an incomplete JSON string" >> {
      val rawJson: String =
        """
        {
          "_id": 1,
          "url": "http://initech.zendesk.com/api/v2/users/1.json",
          "external_id": "74341f74-9c79-49d5-9611-87ef9b6eb75f",
          "name": "Francisca Rasmussen",
          "created_at": "2016-04-15T05:19:46 -10:00",
          "active": true,
          "shared": false,
          "last_login_at": "2013-08-04T01:03:27 -10:00",
          "phone": "8335-422-718",
          "signature": "Don't Worry Be Happy!",
          "tags": [],
          "suspended": true
        }
      """

      "should not correctly parse" >> {
        val result: Either[Error, User] = decode[User](rawJson)

        result must beLeft
      }
    }

    "with a minimal JSON string" >> {
      val rawJson: String =
        """
        {
          "_id": 1,
          "url": "http://initech.zendesk.com/api/v2/users/1.json",
          "external_id": "74341f74-9c79-49d5-9611-87ef9b6eb75f",
          "name": "Francisca Rasmussen",
          "created_at": "2016-04-15T05:19:46 -10:00",
          "active": true,
          "shared": false,
          "last_login_at": "2013-08-04T01:03:27 -10:00",
          "phone": "8335-422-718",
          "signature": "Don't Worry Be Happy!",
          "tags": [],
          "suspended": true,
          "role": "admin"
        }
      """

      "should correctly parse" >> {
        val result: Either[Error, User] = decode[User](rawJson)

        result must beRight(
          User(
            _id = 1,
            url = "http://initech.zendesk.com/api/v2/users/1.json",
            external_id = "74341f74-9c79-49d5-9611-87ef9b6eb75f",
            name = "Francisca Rasmussen",
            alias = None,
            created_at = OffsetDateTime.parse("2016-04-15T05:19:46-10:00"),
            active = true,
            verified = None,
            shared = false,
            locale = None,
            timezone = None,
            last_login_at = OffsetDateTime.parse("2013-08-04T01:03:27-10:00"),
            email = None,
            phone = "8335-422-718",
            signature= "Don't Worry Be Happy!",
            organization_id = None,
            tags = List(),
            suspended = true,
            role = "admin"
          )
        )
      }
    }

    "with a complete JSON string" >> {
      val rawJson: String =
        """
        {
          "_id": 1,
          "url": "http://initech.zendesk.com/api/v2/users/1.json",
          "external_id": "74341f74-9c79-49d5-9611-87ef9b6eb75f",
          "name": "Francisca Rasmussen",
          "alias": "Miss Coffey",
          "created_at": "2016-04-15T05:19:46 -10:00",
          "active": true,
          "verified": true,
          "shared": false,
          "locale": "en-AU",
          "timezone": "Sri Lanka",
          "last_login_at": "2013-08-04T01:03:27 -10:00",
          "email": "coffeyrasmussen@flotonic.com",
          "phone": "8335-422-718",
          "signature": "Don't Worry Be Happy!",
          "organization_id": 119,
          "tags": [
            "Springville",
            "Sutton",
            "Hartsville/Hartley",
            "Diaperville"
          ],
          "suspended": true,
          "role": "admin"
        }
      """

      "should correctly parse" >> {
        val result: Either[Error, User] = decode[User](rawJson)

        result must beRight(
          User(
            _id = 1,
            url = "http://initech.zendesk.com/api/v2/users/1.json",
            external_id = "74341f74-9c79-49d5-9611-87ef9b6eb75f",
            name = "Francisca Rasmussen",
            alias = Some("Miss Coffey"),
            created_at = OffsetDateTime.parse("2016-04-15T05:19:46-10:00"),
            active = true,
            verified = Some(true),
            shared = false,
            locale = Some("en-AU"),
            timezone = Some("Sri Lanka"),
            last_login_at = OffsetDateTime.parse("2013-08-04T01:03:27-10:00"),
            email = Some("coffeyrasmussen@flotonic.com"),
            phone = "8335-422-718",
            signature= "Don't Worry Be Happy!",
            organization_id = Some(119),
            tags = List(
              "Springville",
              "Sutton",
              "Hartsville/Hartley",
              "Diaperville"
            ),
            suspended = true,
            role = "admin"
          )
        )
      }
    }
  }
}