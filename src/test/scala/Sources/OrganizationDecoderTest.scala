package Sources

import java.time.OffsetDateTime

import io.circe._
import io.circe.generic.auto._
import io.circe.parser._

import Sources.OffsetDateTimeParsing._

class OrganizationDecoderTest extends org.specs2.mutable.Specification {
  "Decoding Organization" >> {
    "With an invalid JSON string" >> {
      val rawJson: String =
        """
          {
            "_id": 101,
            "url": "http://initech.zendesk.com/api/v2/organizations/101.json",
            "external_id": "9270ed79-35eb-4a38-a46f-35725197ea8d",
            "name": "Enthaze",
            "domain_names": [],
            "created_at": "2016-05-21T11:10:28 -10:00",
            "details": "MegaCorp",
            "shared_tickets": false,
            "tags": [],
          }
        """

      "should not correctly parse" >> {
        val result: Either[Error, Organization] = decode[Organization](rawJson)

        result must beLeft
      }
    }

    "With an incomplete JSON string" >> {
      val rawJson: String =
        """
          {
            "_id": 101,
            "url": "http://initech.zendesk.com/api/v2/organizations/101.json",
            "external_id": "9270ed79-35eb-4a38-a46f-35725197ea8d",
            "name": "Enthaze",
            "domain_names": [],
            "created_at": "2016-05-21T11:10:28 -10:00",
            "details": "MegaCorp",
            "shared_tickets": false
          }
        """

      "should not correctly parse" >> {
        val result: Either[Error, Organization] = decode[Organization](rawJson)

        result must beLeft
      }
    }

    "With a minimal JSON string" >> {
      val rawJson: String =
        """
          {
            "_id": 101,
            "url": "http://initech.zendesk.com/api/v2/organizations/101.json",
            "external_id": "9270ed79-35eb-4a38-a46f-35725197ea8d",
            "name": "Enthaze",
            "domain_names": [],
            "created_at": "2016-05-21T11:10:28 -10:00",
            "details": "MegaCorp",
            "shared_tickets": false,
            "tags": []
          }
        """

      "should correctly parse" >> {
        val result: Either[Error, Organization] = decode[Organization](rawJson)

        result must beRight(
          Organization(
            _id = 101,
            url = "http://initech.zendesk.com/api/v2/organizations/101.json",
            external_id = "9270ed79-35eb-4a38-a46f-35725197ea8d",
            name = "Enthaze",
            domain_names = Seq(),
            created_at = OffsetDateTime.parse("2016-05-21T11:10:28-10:00"),
            details = "MegaCorp",
            shared_tickets = false,
            tags = Seq()
          )
        )
      }
    }

    "With a complete JSON string" >> {
      val rawJson: String =
        """
          {
            "_id": 101,
            "url": "http://initech.zendesk.com/api/v2/organizations/101.json",
            "external_id": "9270ed79-35eb-4a38-a46f-35725197ea8d",
            "name": "Enthaze",
            "domain_names": [
              "kage.com",
              "ecratic.com",
              "endipin.com",
              "zentix.com"
            ],
            "created_at": "2016-05-21T11:10:28 -10:00",
            "details": "MegaCorp",
            "shared_tickets": false,
            "tags": [
              "Fulton",
              "West",
              "Rodriguez",
              "Farley"
            ]
          }
        """

      "should correctly parse" >> {
        val result: Either[Error, Organization] = decode[Organization](rawJson)

        result must beRight(
          Organization(
            _id = 101,
            url = "http://initech.zendesk.com/api/v2/organizations/101.json",
            external_id = "9270ed79-35eb-4a38-a46f-35725197ea8d",
            name = "Enthaze",
            domain_names = Seq(
              "kage.com",
              "ecratic.com",
              "endipin.com",
              "zentix.com"
            ),
            created_at = OffsetDateTime.parse("2016-05-21T11:10:28-10:00"),
            details = "MegaCorp",
            shared_tickets = false,
            tags = Seq(
              "Fulton",
              "West",
              "Rodriguez",
              "Farley"
            )
          )
        )
      }
    }
  }
}