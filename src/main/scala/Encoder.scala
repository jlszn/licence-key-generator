import java.security.MessageDigest
import java.sql.Date
import java.time.LocalDate

object Encoder {

  def encode(domain: String, date: Date): String = {

  }

  def checkSum(input: String): String = {

    Integer.toHexString(Integer.parseInt(input, 16) % 2 ^ (Security.checksumLength * Security.bitesInHex))

  }

  def domainEncode(domain: String): Seq[String] = {

    val messageDigest = MessageDigest.getInstance("SHA-256")
    val encodedNumber = BigInt(new String(messageDigest.digest(domain.getBytes())).toUpperCase)


  }

}
