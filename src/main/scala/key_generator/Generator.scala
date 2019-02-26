package key_generator

import java.security.MessageDigest
import java.time.LocalDate

object Generator extends App {

  println(generate("domain.com", LocalDate.of(1399, 12, 12)))

  /**
   *
   * @param domain domain name, used in key generation and verification
   * @param date key expiration date, used in key generation
   * @return outputs generated licence key
   */
  def generate(domain: String, date: LocalDate): String = {

    val encodedDomain = domainEncode(domain)
    val encodedDate = dateEncode(date)

    val withoutChecksum: String = encodedDomain.head +
      encodedDate(1) +
      encodedDomain(1) +
      encodedDate(2) +
      encodedDomain(2) +
      encodedDate.head +
      encodedDomain(3)

    checksum(withoutChecksum) + withoutChecksum

  }

  def checksum(input: String): String = Util.crc16(BigInt(input, 16).toByteArray)

  def domainEncode(domain: String): Seq[String] = {

    val messageDigest = MessageDigest.getInstance("SHA-512")
    val encodedNumber = BigInt(messageDigest.digest(domain.getBytes()))

    val domainString = String.format(s"%0${Security.domainLength}X",
      Integer.valueOf((encodedNumber % BigInt(2).pow(40)).toInt))

    Security.domainSplits.map(indexes => domainString.substring(indexes._1, indexes._2))

  }

  def dateEncode(date: LocalDate): Seq[String] = {

    Seq(date.getYear, date.getMonthValue, date.getDayOfMonth)
      .zip(Security.dateIndexes)
      .map(pair => String.format(s"%0${pair._2.size}X", Integer.valueOf(pair._1)))

  }

}
