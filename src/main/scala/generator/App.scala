package generator

import generator.utils.{Messages, Util}

/**
  * This class represents an entrypoint to the application
  */
object App {

  /**
    * A main method that accept commands and theirs parameters
    * @param args parameters used to launch this application
    */
  def main(args: Array[String]): Unit = {
    if (args(0) == Messages.generateKeyCommand) {
      println(Encoder.encode(args(1), Util.parseDateToString(args(2))))
    } else if (args(0) == Messages.validateKeyCommand) {
      Decoder.verify(args(1), args(2))
    } else {
      println(Messages.unknownCommand)
    }
  }

}
