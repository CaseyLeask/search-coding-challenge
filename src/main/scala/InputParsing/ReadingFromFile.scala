package InputParsing

import Sources.{Organization, SearchFailure, Ticket, User}
import io.circe.generic.auto._
import io.circe.parser.decode

import scala.io.Source

import Sources.OffsetDateTimeParsing._

object ReadingFromFile {
  type Sources = (Seq[Organization], Seq[Ticket], Seq[User])

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

  def loadSources: Either[SearchFailure, Sources] = {
    for {
      organizations <- readOrganizations
      tickets <- readTickets
      users <- readUsers
    } yield (organizations, tickets, users)
  }
}
