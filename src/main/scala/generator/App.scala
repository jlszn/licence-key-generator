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

    args(0) match {

      case Messages.generateKeyCommand => println(Encoder.encode(args(1), Util.parseDateToString(args(2))))

      case Messages.validateKeyCommand => Decoder.verify(args(1), args(2))

      case Messages.infoCommand => println(Messages.info)

      case _ => println(Messages.unknownCommand)

    }
  }

}
