package generator

import java.security.MessageDigest
import java.time.LocalDate

import generator.utils.{Messages, Util}

/**
  * This object is used for a key generation
  */
object Encoder {

  /**
    * This method generates a new key based on a domain and an expiration date
    * @param domain a domain to which a key is bound
    * @param date an expiration date of a key
    * @return
    */
  def encode(domain: String, date: LocalDate): String = {

    val encodedDomain = domainEncode(domain)
    val encodedDate = dateEncode(date)

    val withoutChecksum: String = encodedDomain.head +
      encodedDate(1) +
      encodedDomain(1) +
      encodedDate(2) +
      encodedDomain(2) +
      encodedDate.head +
      encodedDomain(3)

    Util.addDelimiters(
      checksum(withoutChecksum) + withoutChecksum, Util.keySegmentLength, Util.keySegmentStep, Messages.delimiter
    )

  }

  /**
    * This method generates a checksum for a key
    * @param input a key without checksum
    * @return a checksum for this key
    */
  def checksum(input: String): String = Util.crc16(BigInt(input, 16).toByteArray)

  /**
    * This method encodes domain as a sequence of HEX-digits combined into sequences of string according to positions
    * in a key
    * @param domain a domain name to encode
    * @return a sequence of hex-strings that represent the domain
    */
  def domainEncode(domain: String): Seq[String] = {

    val messageDigest = MessageDigest.getInstance(Messages.hashingAlgorithm)
    val encodedNumber = BigInt(messageDigest.digest(domain.getBytes()))

    val domainString = String.format(s"%0${Util.domainLength}X",
      Integer.valueOf((encodedNumber % BigInt(2).pow(40)).toInt))

    Util.domainSplits.map(indexes => domainString.substring(indexes._1, indexes._2))

  }

  /**
    * This method encodes an expiration date
    * @param date an expiration date to encode
    * @return a sequence of HEX-strings that represent a date according to positions in a key
    */
  def dateEncode(date: LocalDate): Seq[String] = {

    Seq(date.getYear, date.getMonthValue, date.getDayOfMonth)
      .zip(Util.dateIndexes)
      .map(pair => String.format(s"%0${pair._2.size}X", Integer.valueOf(pair._1)))

  }

}
