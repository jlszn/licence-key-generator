package generator

import generator.utils.Util

object App {

  def main(args: Array[String]): Unit = {
    val domain = java.net.InetAddress.getLocalHost.getHostName

    if (args(0) == "generate-key") {
      println(Encoder.encode(args(1), Util.parseDateToString(args(2))))
    } else if (args(0) == "validate-key") {
      println(Decoder.verify(args(1)))
    } else {
      println("Unknown command")
    }
  }

}
