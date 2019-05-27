package State

import State.CommandLine.{BlankSearchTerm, EnterSearchTerm, EnterSearchValue, NoAction, Organization, QuitProgram, SelectSearchOptions, SelectSourceOptions, Ticket, UnrecognizedCommand, User, ViewSearchableFields, Welcome}

class CommandLineStateTest extends org.specs2.mutable.Specification {
  "Initial State" >> {
    val state = CommandLine.initialState

    "Should be 'Welcome'" >> {
      state must beEqualTo(Welcome)
    }
  }

  "state of 'Welcome'" >> {
    val state = Welcome

    "Given command 'quit'" >> {
      val command = "quit"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'QuitProgram'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(QuitProgram)
        }
      }
    }

    "Given command ''" >> {
      val command = ""

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'NoAction'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(NoAction)
        }

        "Should return a new state of 'SelectSearchOptions'" >> {
          val (_, newState) = result

          newState must beEqualTo(SelectSearchOptions)
        }
      }
    }

    "Given command 'anyotherstring'" >> {
      val command = ""

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'NoAction'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(NoAction)
        }

        "Should return a new state of 'SelectSearchOptions'" >> {
          val (_, newState) = result

          newState must beEqualTo(SelectSearchOptions)
        }
      }
    }
  }

  "state of 'Select Search Options'" >> {
    val state = SelectSearchOptions

    "Given command 'quit'" >> {
      val command = "quit"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'QuitProgram'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(QuitProgram)
        }
      }
    }

    "Given command ''" >> {
      val command = ""

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'UnrecognizedCommand'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(UnrecognizedCommand(command))
        }

        "Should return a new state of 'SelectSearchOptions'" >> {
          val (_, newState) = result

          newState must beEqualTo(SelectSearchOptions)
        }
      }
    }

    "Given command '1'" >> {
      val command = "1"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'NoAction'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(NoAction)
        }

        "Should return a new state of 'SelectSourceOptions'" >> {
          val (_, newState) = result

          newState must beEqualTo(SelectSourceOptions)
        }
      }
    }

    "Given command '2'" >> {
      val command = "2"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'ViewSearchableFields'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(ViewSearchableFields)
        }

        "Should return a new state of 'SelectSearchOptions'" >> {
          val (_, newState) = result

          newState must beEqualTo(SelectSearchOptions)
        }
      }
    }

    "Given command 'anyotherstring'" >> {
      val command = "anyotherstring"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'UnrecognizedCommand'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(UnrecognizedCommand(command))
        }

        "Should return a new state of 'SelectSearchOptions'" >> {
          val (_, newState) = result

          newState must beEqualTo(SelectSearchOptions)
        }
      }
    }
  }

  "state of 'Select Source Options'" >> {
    val state = SelectSourceOptions

    "Given command 'quit'" >> {
      val command = "quit"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'QuitProgram'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(QuitProgram)
        }
      }
    }

    "Given command ''" >> {
      val command = ""

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'UnrecognizedCommand'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(UnrecognizedCommand(command))
        }

        "Should return a new state of 'SelectSourceOptions'" >> {
          val (_, newState) = result

          newState must beEqualTo(SelectSourceOptions)
        }
      }
    }

    "Given command '1'" >> {
      val command = "1"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'NoAction'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(NoAction)
        }

        "Should return a new state of 'EnterSearchTerm(User)'" >> {
          val (_, newState) = result

          newState must beEqualTo(EnterSearchTerm(User))
        }
      }
    }

    "Given command '2'" >> {
      val command = "2"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'NoAction'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(NoAction)
        }

        "Should return a new state of 'EnterSearchTerm(Ticket)'" >> {
          val (_, newState) = result

          newState must beEqualTo(EnterSearchTerm(Ticket))
        }
      }
    }

    "Given command '3'" >> {
      val command = "3"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'NoAction'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(NoAction)
        }

        "Should return a new state of 'EnterSearchTerm(Organization)'" >> {
          val (_, newState) = result

          newState must beEqualTo(EnterSearchTerm(Organization))
        }
      }
    }

    "Given command 'anyotherstring'" >> {
      val command = "anyotherstring"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'UnrecognizedCommand'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(UnrecognizedCommand(command))
        }

        "Should return a new state of 'SelectSourceOptions'" >> {
          val (_, newState) = result

          newState must beEqualTo(SelectSourceOptions)
        }
      }
    }
  }

  "state of 'Enter Search Term for User Source'" >> {
    val source = User
    val state = EnterSearchTerm(source)

    "Given command 'quit'" >> {
      val command = "quit"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'QuitProgram'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(QuitProgram)
        }
      }
    }

    "Given command ''" >> {
      val command = ""

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'BlankSearchTerm'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(BlankSearchTerm)
        }

        "Should return a new state of 'EnterSearchTerm'" >> {
          val (_, newState) = result

          newState must beEqualTo(EnterSearchTerm(source))
        }
      }
    }

    "Given command 'anyotherstring'" >> {
      val command = "anyotherstring"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'NoAction'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(NoAction)
        }

        "Should return a new state of 'EnterSearchValue'" >> {
          val (_, newState) = result

          newState must beEqualTo(EnterSearchValue(source, command))
        }
      }
    }
  }

  "state of 'Enter Search Term for Ticket Source'" >> {
    val source = Ticket
    val state = EnterSearchTerm(source)

    "Given command 'quit'" >> {
      val command = "quit"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'QuitProgram'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(QuitProgram)
        }
      }
    }

    "Given command ''" >> {
      val command = ""

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'BlankSearchTerm'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(BlankSearchTerm)
        }

        "Should return a new state of 'EnterSearchTerm'" >> {
          val (_, newState) = result

          newState must beEqualTo(EnterSearchTerm(source))
        }
      }
    }

    "Given command 'anyotherstring'" >> {
      val command = "anyotherstring"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'NoAction'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(NoAction)
        }

        "Should return a new state of 'EnterSearchValue'" >> {
          val (_, newState) = result

          newState must beEqualTo(EnterSearchValue(source, command))
        }
      }
    }
  }

  "state of 'Enter Search Term for Organization Source'" >> {
    val source = Organization
    val state = EnterSearchTerm(source)

    "Given command 'quit'" >> {
      val command = "quit"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'QuitProgram'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(QuitProgram)
        }
      }
    }

    "Given command ''" >> {
      val command = ""

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'BlankSearchTerm'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(BlankSearchTerm)
        }

        "Should return a new state of 'EnterSearchTerm'" >> {
          val (_, newState) = result

          newState must beEqualTo(EnterSearchTerm(source))
        }
      }
    }

    "Given command 'anyotherstring'" >> {
      val command = "anyotherstring"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'NoAction'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(NoAction)
        }

        "Should return a new state of 'EnterSearchValue'" >> {
          val (_, newState) = result

          newState must beEqualTo(EnterSearchValue(source, command))
        }
      }
    }
  }

  "state of 'Enter Search Value for User Source'" >> {
    val source = User
    val term = "anyterm"
    val state = EnterSearchValue(source, term)

    "Given command 'quit'" >> {
      val command = "quit"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'QuitProgram'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(QuitProgram)
        }
      }
    }

    "Given command ''" >> {
      val command = ""

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'MakeSearch'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(CommandLine.MakeSearch(source, term, command))
        }

        "Should return a new state of 'Welcome'" >> {
          val (_, newState) = result

          newState must beEqualTo(Welcome)
        }
      }
    }

    "Given command 'anyotherstring'" >> {
      val command = "anyotherstring"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'MakeSearch'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(CommandLine.MakeSearch(source, term, command))
        }

        "Should return a new state of 'Welcome'" >> {
          val (_, newState) = result

          newState must beEqualTo(Welcome)
        }
      }
    }
  }

  "state of 'Enter Search Value for Ticket Source'" >> {
    val source = Ticket
    val term = "anyterm"
    val state = EnterSearchValue(source, term)

    "Given command 'quit'" >> {
      val command = "quit"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'QuitProgram'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(QuitProgram)
        }
      }
    }

    "Given command ''" >> {
      val command = ""

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'MakeSearch'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(CommandLine.MakeSearch(source, term, command))
        }

        "Should return a new state of 'Welcome'" >> {
          val (_, newState) = result

          newState must beEqualTo(Welcome)
        }
      }
    }

    "Given command 'anyotherstring'" >> {
      val command = "anyotherstring"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'MakeSearch'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(CommandLine.MakeSearch(source, term, command))
        }

        "Should return a new state of 'Welcome'" >> {
          val (_, newState) = result

          newState must beEqualTo(Welcome)
        }
      }
    }
  }

  "state of 'Enter Search Value for Organization Source'" >> {
    val source = Ticket
    val term = "anyterm"
    val state = EnterSearchValue(source, term)

    "Given command 'quit'" >> {
      val command = "quit"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'QuitProgram'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(QuitProgram)
        }
      }
    }

    "Given command ''" >> {
      val command = ""

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'MakeSearch'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(CommandLine.MakeSearch(source, term, command))
        }

        "Should return a new state of 'Welcome'" >> {
          val (_, newState) = result

          newState must beEqualTo(Welcome)
        }
      }
    }

    "Given command 'anyotherstring'" >> {
      val command = "anyotherstring"

      "When calling 'nextState'" >> {
        val result = CommandLine.nextState(state, command)

        "Should return a new action of 'MakeSearch'" >> {
          val (newAction, _) = result

          newAction must beEqualTo(CommandLine.MakeSearch(source, term, command))
        }

        "Should return a new state of 'Welcome'" >> {
          val (_, newState) = result

          newState must beEqualTo(Welcome)
        }
      }
    }
  }

}
