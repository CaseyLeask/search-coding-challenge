import java.time.OffsetDateTime

import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import search.{Organization, SearchFailure, Ticket, User}
import search.OffsetDateTimeParsing._

import scala.io.Source

object Main {
  def readFile(location: String): String = {
    val source = Source.fromFile(location)
    val sourceFile = source.mkString
    source.close()

    sourceFile
  }

  private def readOrganizations = {
    val organizationsFilename = "data/organizations.json"
    decode[Seq[Organization]](readFile(organizationsFilename))
      .left
      .map(e => SearchFailure(s"Error parsing $organizationsFilename: ${e.getLocalizedMessage}"))
  }

  private def readTickets = {
    val ticketsFilename = "data/tickets.json"
    decode[Seq[Ticket]](readFile(ticketsFilename)).left.map(e =>
      SearchFailure(s"Error parsing $ticketsFilename: ${e.getLocalizedMessage}")
    )
  }

  private def readUsers = {
    val usersFilename = "data/users.json"
    decode[Seq[User]](readFile(usersFilename)).left.map(e =>
      SearchFailure(s"Error parsing $usersFilename: ${e.getLocalizedMessage}")
    )
  }

  private def loadSources = {
    for {
      organizations <- readOrganizations
      tickets <- readTickets
      users <- readUsers
    } yield (organizations, tickets, users)
  }

  def main(args: Array[String]) = {
    loadSources match {
      case Left(error) => {
        Console.println(error)
        System.exit(-1)
      }
      case Right((organizations, tickets, users)) => {
        Console.println(organizations, tickets, users)
      }
    }
  }
}