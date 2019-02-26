package generator

import generator.utils.Util

/**
  * This class represents an entrypoint to the application
  */
object App {

  /**
    * A main method that accept commands and theirs parameters
    * @param args parameters used to launch this application
    */
  def main(args: Array[String]): Unit = {
    val domain = java.net.InetAddress.getLocalHost.getHostName

    if (args(0) == "generate-key") {
      println(Encoder.encode(args(1), Util.parseDateToString(args(2))))
    } else if (args(0) == "validate-key") {
      println(Decoder.verify(args(1), args(2)))
    } else {
      println("Unknown command")
    }
  }

}
