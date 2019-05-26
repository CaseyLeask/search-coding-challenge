import java.time.OffsetDateTime

import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import search.Ticket
import search.Ticket._
import search.OffsetDateTimeParsing._

class TicketDecoderTest extends org.specs2.mutable.Specification {
  "Decoding Ticket" >> {
    "With valid JSON" >> {
      val rawJson: String =
        """
        {
          "_id": "436bf9b0-1147-4c0a-8439-6f79833bff5b",
          "url": "http://initech.zendesk.com/api/v2/tickets/436bf9b0-1147-4c0a-8439-6f79833bff5b.json",
          "external_id": "9210cdc9-4bee-485f-a078-35396cd74063",
          "created_at": "2016-04-28T11:19:34 -10:00",
          "type": "incident",
          "subject": "A Catastrophe in Korea (North)",
          "description": "Nostrud ad sit velit cupidatat laboris ipsum nisi amet laboris ex exercitation amet et proident. Ipsum fugiat aute dolore tempor nostrud velit ipsum.",
          "priority": "high",
          "status": "pending",
          "submitter_id": 38,
          "assignee_id": 24,
          "organization_id": 116,
          "tags": [
            "Ohio",
            "Pennsylvania",
            "American Samoa",
            "Northern Mariana Islands"
          ],
          "has_incidents": false,
          "due_at": "2016-07-31T02:37:50 -10:00",
          "via": "web"
        }
      """

      "should correctly parse" >> {
        val result: Either[Error, Ticket] = decode[Ticket](rawJson)

        result must beRight(
          Ticket(
            _id = "436bf9b0-1147-4c0a-8439-6f79833bff5b",
            url = "http://initech.zendesk.com/api/v2/tickets/436bf9b0-1147-4c0a-8439-6f79833bff5b.json",
            external_id = "9210cdc9-4bee-485f-a078-35396cd74063",
            created_at = OffsetDateTime.parse("2016-04-28T11:19:34-10:00"), //TODO deal with extra space in input
            ticket_type = Some("incident"),
            subject = "A Catastrophe in Korea (North)",
            description = Some("Nostrud ad sit velit cupidatat laboris ipsum nisi amet laboris ex exercitation amet et proident. Ipsum fugiat aute dolore tempor nostrud velit ipsum."),
            priority = "high",
            status = "pending",
            submitter_id = 38,
            assignee_id = Some(24),
            organization_id = Some(116),
            tags = Seq(
              "Ohio",
              "Pennsylvania",
              "American Samoa",
              "Northern Mariana Islands"
            ),
            has_incidents = false,
            due_at = Some(OffsetDateTime.parse("2016-07-31T02:37:50-10:00")),
            via = "web"
          )
        )
      }
    }
  }
}