package generator

import java.time.LocalDate

import generator.utils.{Messages, Util}

/**
  * This object is used for a key verification and parsing.
  */
object Decoder {

  //positions of date values in key
  val DAY_POSITIONS: (Int, Int) = (6, 7)
  val MONTH_POSITIONS: (Int, Int) = (10, 12)
  val YEAR_POSITIONS: (Int, Int) = (13, 16)

  /**
    * This method checks a checksum of a key. If it's wrong, then the whole key is wrong
    * @param checksum extracted checksum
    * @param key key without checksum
    * @return true if checksum is right, false otherwise
    */
  def checkChecksum(checksum: String, key: String): Boolean = {
    checksum.equals(Util.crc16(BigInt(key, Util.radix).toByteArray))
  }

  /**
    * This method checks a domain
    * @param key a key to check
    * @param domain a domain of a machine
    * @return true if encoded domain and passed domain are equal, false otherwise
    */
  def checkDomain(key: String, domain: String): Boolean = {
    val domainInKey = Util.domainExtractSplits
      .map(tuple => key.substring(tuple._1, tuple._2)).mkString("")

    val checkResult = Encoder.domainEncode(domain).mkString == domainInKey

    if (!checkResult) {
      println(Messages.invalidDomain)
    }

    checkResult
  }

  /**
    * This method checks if a working period of a key is expired
    * @param key a key to check
    * @return true if an encoded date is valid
    */
  def isExpired(key: String): Boolean = {
    val month = Integer.parseInt(key.substring(MONTH_POSITIONS._1, MONTH_POSITIONS._2), Util.radix)
    val day = Integer.parseInt(key.substring(DAY_POSITIONS._1, DAY_POSITIONS._2), Util.radix)
    val year = Integer.parseInt(key.substring(YEAR_POSITIONS._1, YEAR_POSITIONS._2), Util.radix)

    val checkResult = LocalDate.of(year, month, day).isAfter(LocalDate.now())

    if (!checkResult) {
      println(Messages.expiredDate)
    }

    checkResult
  }

  /**
    * This method checks if a key is valid
    * @param key a key to check
    * @return true if the key is valid, false otherwise
    */
  def verify(key: String, domain: String): Boolean = {
    val clearKey = key.split(Messages.delimiter).mkString

    if (clearKey.length != Util.keyLength) {
      println(Messages.invalidKey)
      false
    } else {

      val res = checkChecksum(

        clearKey.substring(Util.checksumExtractSplits._1, Util.checksumExtractSplits._2), clearKey.substring(Util.checksumExtractSplits._2)

      ) && isExpired(clearKey) && checkDomain(clearKey, domain)

      if(res) println(Messages.validKey) else println(Messages.invalidKey)
      res

    }
  }

}
