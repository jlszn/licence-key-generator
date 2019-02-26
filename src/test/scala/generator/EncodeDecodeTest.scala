package generator

import java.time.LocalDate

import org.scalatest.{FlatSpec, Matchers}

class EncodeDecodeTest extends FlatSpec with Matchers {
  "Decoder method isExpired" should "check date in key for expiration and return true" in {
    val date = LocalDate.of(2020, 11, 11)

    val key = Encoder.encode("domain.com", date).split("-").mkString

    Decoder.isExpired(key) shouldBe true
  }

  "Decoder method isExpired" should "check date in key for expiration and return false" in {
    val date = LocalDate.of(2000, 11, 11)

    val key = Encoder.encode("domain.com", date).split("-").mkString

    Decoder.isExpired(key) shouldBe false
  }

  "Decoder method verify" should "return false when date is invalid" in {
    val date = LocalDate.of(2000, 11, 11)

    val key = Encoder.encode("domain.com", date).split("-").mkString

    Decoder.verify(key, "domain.com") shouldBe false
  }

  "Decoder method verify" should "return false when domain is invalid" in {
    val date = LocalDate.of(2000, 11, 11)

    val key = Encoder.encode("domain.com", date)

    Decoder.verify(key, "domain.co") shouldBe false
  }

  "Decoder method verify" should "return true when all data is fine" in {
    val date = LocalDate.of(2020, 11, 11)

    val key = Encoder.encode("domain.com", date)

    Decoder.verify(key, "domain.com") shouldBe true
  }

  "Encoder method encode" should "return equals values for the same input" in {
    val domain = "domain.com"

    val date = LocalDate.of(2020, 11, 11)

    Encoder.encode(domain, date) shouldEqual Encoder.encode(domain, date)
  }

}
