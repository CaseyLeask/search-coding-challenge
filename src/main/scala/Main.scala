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

  val organizationsFilename = "data/organizations.json"
  def readOrganizationsFile() = readFile(organizationsFilename)

  val ticketsFilename = "data/tickets.json"
  def readTicketsFile() = readFile(ticketsFilename)

  val usersFilename = "data/users.json"
  def readUsersFile() = readFile(usersFilename)

  def main(args: Array[String]) = {
    val organizations = decode[Seq[Organization]](readOrganizationsFile())

    val flattenedOrganizationsResult = organizations match {
      case Left(e) => SearchFailure(s"Error parsing $organizationsFilename: ${e.getLocalizedMessage}").asJson
      case Right(organizationsSuccess) => organizationsSuccess.asJson
    }

    Console.println(flattenedOrganizationsResult)

    val tickets = decode[Seq[Ticket]](readTicketsFile())

    val flattenedTicketsResult = tickets match {
      case Left(e) => SearchFailure(s"Error parsing $ticketsFilename: ${e.getLocalizedMessage}").asJson
      case Right(ticketsSuccess) => ticketsSuccess.asJson
    }

    Console.println(flattenedTicketsResult)

    val users = decode[Seq[User]](readUsersFile())

    val flattenedUsersResult = users match {
      case Left(e) => SearchFailure(s"Error parsing $usersFilename: ${e.getLocalizedMessage}").asJson
      case Right(usersSuccess) => usersSuccess.asJson
    }

    Console.println(flattenedUsersResult)
  }
}