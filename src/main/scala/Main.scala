import InputParsing.{ConsoleInteraction, ReadingFromFile}

object Main {

  def main(args: Array[String]): Unit = ReadingFromFile.loadSources match {
    case Left(error) => {
      Console.println(error)
      System.exit(-1)
    }
    case Right(sources) => {
      ConsoleInteraction.begin(sources)
    }
  }
}