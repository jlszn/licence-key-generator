package key_generator

import java.security.MessageDigest
import java.time.LocalDate

object Encoder extends App {

  println(dateEncode(LocalDate.of(1999, 12,30)))

  def encode(domain: String, date: LocalDate): String = {

  }

  def checkSum(input: String): String = Util.crc16(BigInt(input, 16).toByteArray)

  def domainEncode(domain: String): Seq[String] = {

    val messageDigest = MessageDigest.getInstance("SHA-512")
    val encodedNumber = BigInt(messageDigest.digest(domain.getBytes()))

    val domainString = String.format(s"%0${Security.domainLength}X", Integer.valueOf((encodedNumber % BigInt(2).pow(40)).toInt))

    Security.domainSplits.map(indexes => domainString.substring(indexes._1, indexes._2))

  }

  def dateEncode(date: LocalDate): Seq[String] = {

    Seq(date.getYear, date.getMonthValue, date.getDayOfMonth).zip(Security.dateIndexes).map(pair => String.format(s"%0${pair._2.size}X", Integer.valueOf(pair._1)))

  }

}
