package key_generator

import java.time.LocalDate

import key_generator.utils.Util

object Decoder {

  /**
    *
    * @param checksum
    * @param key
    * @return
    */
  def checkChecksum(checksum: String, key: String): Boolean = {
    checksum.equals(Util.crc16(BigInt(key, 16).toByteArray))
  }

  /**
    *
    * @param key
    * @return
    */
  def isExpired(key: String): Boolean = {
    val month = Integer.parseInt(key.substring(6, 7), 16)
    val day = Integer.parseInt(key.substring(10, 12), 16)
    val year = Integer.parseInt(key.substring(13, 16), 16)

    LocalDate.of(year, month, day).isAfter(LocalDate.now())
  }

  /**
    *
    * @param key
    * @return
    */
  def verify(key: String): Boolean = {
    val clearKey = key.split("-").mkString

    isExpired(clearKey) && checkChecksum(clearKey.substring(0, 4), clearKey.substring(4))
  }

}
