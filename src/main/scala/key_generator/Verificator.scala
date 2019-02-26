package key_generator

import java.time.LocalDate

import key_generator.utils.Util

object Verificator {

  /**
   *
   * @return output message 'valid' or 'invalid'
   */

  def main(args: Array[String]): Unit = {
    println(verify("0686-002E-1E1A-77E4-639C"))
  }

  def checkChecksum(checksum: String, key: String): Boolean = {
    checksum.equals(Util.crc16(BigInt(key, 16).toByteArray))
  }

  def isExpired(key: String): Boolean = {
    val month = Integer.parseInt(key.substring(6, 7), 16)
    val day = Integer.parseInt(key.substring(10, 12), 16)
    val year = Integer.parseInt(key.substring(13, 16), 16)

    LocalDate.of(year, month, day).isAfter(LocalDate.now())
  }

  def verify(key: String): Boolean = {
    val clearKey = key.split("-").mkString

    isExpired(clearKey) && checkChecksum(clearKey.substring(0, 4), clearKey.substring(4))
  }

}
