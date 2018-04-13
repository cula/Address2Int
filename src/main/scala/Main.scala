package scala

import scopt.OptionParser

object Main extends App {

  case class Config(in: String)

  val parser: OptionParser[Config] = new scopt.OptionParser[Config]("java -jar <path-to-your-jar>") {
    head("Address2Int", "0.1")

    opt[String]('i', "in").required()
      .valueName("<value>")
      .action( (x, c) => c.copy(in = x))
      .text("in is the address.")

    help("help").text("prints this usage text")
  }

  parser.parse(args, Config("")) match {
    case None => println("Illegal parameters. Please use '--help' to see the usage.")
    case Some(c) =>
      try {
        val result = Utils.address2Int(c.in)
        println(result)
      } catch {
        case e: InvalidAddressException => println(s"Error: ${e.message}")
        case e: Throwable => throw e
      }
  }
}
