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

    //commented because for testing with different domains
//    val currentMachineDomain = java.net.InetAddress.getLocalHost.getHostName

    args(0) match {

      case Messages.generateKeyCommand =>
        if (args.length < 2) {
          println(Messages.emptyDomain)
        } else if (args.length < 3) {
          println(Messages.emptyDate)
        } else {
          println(Encoder.encode(args(1), Util.parseDateToString(args(2))))
        }

      case Messages.validateKeyCommand =>
        if (args.length < 2) {
          println(Messages.emptyKey)
        } else if (args.length < 3) {
          println(Messages.emptyDomain)
        } else {
          Decoder.verify(args(1), args(2))
        }

      case Messages.infoCommand => println(Messages.info)

      case _ => println(Messages.unknownCommand)

    }
  }

}
