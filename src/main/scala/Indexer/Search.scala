package Indexer

import InputParsing.ReadingFromFile.Sources
import State.CommandLine

object Search {
  def makeSearch(sources: Sources, selection: CommandLine.SourceSelection, str: String, str1: String) = Unit

  def viewSearchableFields(sources: Sources) = {
    val (organizations, tickets, users) = sources

    Console.println("Search Users with")


    Console.println("Search Tickets with")


    Console.println("Search Organizations with")
  }
}
