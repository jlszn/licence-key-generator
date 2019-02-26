package generator

import java.time.LocalDate

import generator.utils.Util

/**
  * This object is used for a key verification and parsing.
  */
object Decoder {

  /**
    * This method checks a checksum of a key. If it's wrong, then the whole key is wrong
    * @param checksum extracted checksum
    * @param key key without checksum
    * @return true if checksum is right, false otherwise
    */
  def checkChecksum(checksum: String, key: String): Boolean = {
    checksum.equals(Util.crc16(BigInt(key, 16).toByteArray))
  }

  /**
    * This method checks a domain
    * @param key a key to check
    * @param domain a domain of a machine
    * @return true if encoded domain and passed domain are equal, false otherwise
    */
  def checkDomain(key: String, domain: String): Boolean = {
    val domainInKey = key.substring(4, 6) + key.substring(7, 10) + key.substring(12, 13) + key.substring(16)

    val checkResult = Encoder.domainEncode(domain).mkString == domainInKey

    if (!checkResult) {
      println("Your domain is invalid!")
    }

    checkResult
  }

  /**
    * This method checks if a working period of a key is expired
    * @param key a key to check
    * @return true if an encoded date is valid
    */
  def isExpired(key: String): Boolean = {
    val month = Integer.parseInt(key.substring(6, 7), 16)
    val day = Integer.parseInt(key.substring(10, 12), 16)
    val year = Integer.parseInt(key.substring(13, 16), 16)

    val checkResult = LocalDate.of(year, month, day).isAfter(LocalDate.now())

    if (!checkResult) {
      println("Your key is expired!")
    }

    checkResult
  }

  /**
    * This method checks if a key is valid
    * @param key a key to check
    * @return true if the key is valid, false otherwise
    */
  def verify(key: String, domain: String): Boolean = {
    val clearKey = key.split("-").mkString

    if (clearKey.length != 20) {
      println("Key is invalid!")
      false
    } else {
      checkChecksum(clearKey.substring(0, 4), clearKey.substring(4)) && isExpired(clearKey) && checkDomain(clearKey, domain)
    }
  }

}
