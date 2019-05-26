package State

object CommandLine {
  sealed trait SourceSelection
  case object Organization extends SourceSelection
  case object Ticket extends SourceSelection
  case object User extends SourceSelection

  sealed trait CommandState
  case object Welcome extends CommandState
  case object SelectSearchOptions extends CommandState
  case object SelectSourceOptions extends CommandState
  case class EnterSearchTerm(sourceSelection: SourceSelection) extends CommandState
  case class EnterSearchValue(sourceSelection: SourceSelection, searchTerm: String) extends CommandState

  sealed trait CommandAction
  case object ViewSearchableFields extends CommandAction
  case class MakeSearch(sourceSelection: SourceSelection, searchTerm: String, searchValue: String) extends CommandAction
  case object NoAction extends CommandAction
  case class UnrecognizedCommand(text: String) extends CommandAction
  case object BlankSearchTerm extends CommandAction
  case object QuitProgram extends CommandAction

  def initialState = Welcome

  def nextState(state: CommandState, command: String): (CommandAction, CommandState) = (state, command) match {
    case (anyState, "quit") => (QuitProgram, anyState)
    case (Welcome, _) => (
      NoAction,
      SelectSearchOptions
    )
    case (SelectSearchOptions, "1") => (
      NoAction,
      SelectSourceOptions
    )
    case (SelectSearchOptions, "2") => (
      ViewSearchableFields,
      SelectSearchOptions
    )
    case (SelectSearchOptions, unrecognized) => (
      UnrecognizedCommand(unrecognized),
      SelectSearchOptions
    )
    case (SelectSourceOptions, "1") => (
      NoAction,
      EnterSearchTerm(User)
    )
    case (SelectSourceOptions, "2") => (
      NoAction,
      EnterSearchTerm(Ticket)
    )
    case (SelectSourceOptions, "3") => (
      NoAction,
      EnterSearchTerm(Organization)
    )
    case (SelectSourceOptions, unrecognized) => (
      UnrecognizedCommand(unrecognized),
      SelectSourceOptions
    )
    case (existingState@EnterSearchTerm(_), "") => (
      BlankSearchTerm,
      existingState
    )
    case (EnterSearchTerm(sourceSelection), searchTerm) => (
      NoAction,
      EnterSearchValue(sourceSelection, searchTerm)
    )
    case (EnterSearchValue(sourceSelection, searchTerm), searchValue) => (
      MakeSearch(sourceSelection, searchTerm, searchValue),
      Welcome
    )
  }
}
