package InputParsing

import Indexer.Search
import InputParsing.ReadingFromFile.Sources
import State.CommandLine
import State.CommandLine.{BlankSearchTerm, CommandAction, CommandState, EnterSearchTerm, EnterSearchValue, MakeSearch, NoAction, QuitProgram, SelectSearchOptions, SelectSourceOptions, SourceSelection, UnrecognizedCommand, ViewSearchableFields, Welcome}

import scala.io.Source


object ConsoleInteraction {
  def begin(sources: Sources) = {
    execute(sources, Source.stdin.getLines())
  }

  def takeAction(sources: Sources, action: CommandAction): Unit = action match {
    case MakeSearch(sourceSelection, searchTerm, searchValue) => Search.makeSearch(sources, sourceSelection, searchTerm, searchValue)
    case ViewSearchableFields => Search.viewSearchableFields(sources)
    case NoAction => Unit
    case UnrecognizedCommand(text) => Console.println(s"Sorry! Couldn't recognise '$text'")
    case BlankSearchTerm => Console.println("Sorry! We don't support a blank search term")
    case QuitProgram => System.exit(0)
  }

  def printState(state: CommandState): Unit = state match {
    case Welcome => {
      Console.println("""
                        |Welcome to Zendesk Search
                        |Type 'quit' to exit at any time, Press 'Enter' to continue
                      """.stripMargin)
    }
    case SelectSearchOptions => {
      Console.println("""
                        |Select search options:
                        |* Press 1 to search Zendesk
                        |* Press 2 to view a list of searchable fields
                        |* Type 'quit' to exit
                      """.stripMargin)
    }
    case SelectSourceOptions => {
      Console.println("""
                        |Select 1) Users or 2) Tickets or 3) Organizations
                      """.stripMargin)
    }
    case EnterSearchTerm(_) => {
      Console.println("Enter search term")
    }
    case EnterSearchValue(_, _) => {
      Console.println("Enter search value")
    }

  }

  def execute(sources: Sources, commands: TraversableOnce[String]) = {
    val initialState = CommandLine.initialState

    printState(initialState)

    commands.foldLeft[CommandState](initialState)((state, command) => {
      val (action: CommandAction, newState: CommandState) = CommandLine.nextState(state, command)

      takeAction(sources, action)
      printState(newState)

      newState
    })
  }
}
