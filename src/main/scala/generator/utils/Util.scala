package generator.utils

import java.time.LocalDate

/**
  * This utilitarian class contains useful constants and methods.
  */
object Util {

  /**
    * A radix of symbols
    */
  val radix = 16

  /**
    * A length of a key
    */
  val keyLength = 20

  /**
    * A sequence that represents places where a checksum is placed
    */
  val checksumIndexes: Seq[Int] = Seq(0, 1, 2, 3)

  /**
    * A sequence that represents places where a domain is placed
    */
  val domainIndexes: Seq[Seq[Int]] = Seq(Seq(4, 5), Seq(7, 8, 9), Seq(12), Seq(16, 17, 18, 19))

  /**
    * A sequence that describes how to divide an entire key
    */
  val domainDivideSplits: Seq[(Int, Int)] = domainIndexes
    .map(_.size)
    .scanLeft(0)(_ + _).tail
    .scanLeft((0,0))((prev, i) => (prev._2, i) ).tail

  /**
    * A sequence that describes how to split a key to extract a domain
    */
  val domainExtractSplits: Seq[(Int, Int)] = domainIndexes
    .map(seq => (seq.min, seq.max + 1))

  /**
    * A sequence that describes how to split a key to extract a checksum
    */
  val checksumExtractSplits: (Int, Int) = (checksumIndexes.min, checksumIndexes.max + 1)

  /**
    * A sequence that represents places where an expiration date is placed
    * Order is sensible DO NOT CHANGE THE ORDER. YEAR-MONTH-DAY
    * 10, 11 - DAY
    * 6 - MONTH
    * 13, 14, 15 - YEAR
    */
  val dateIndexes: Seq[Seq[Int]] = Seq(Seq(13, 14, 15), Seq(6), Seq(10, 11))

  /**
    * A sequence that describes how to split a key to extract a date
    */
  val dateSplits: Map[String, (Int, Int)] = Seq(Messages.yearSymbol, Messages.monthSymbol, Messages.daySymbol).zip (
    dateIndexes.map(seq => (seq.min, seq.max + 1))
  ).toMap

  /**
    * An amount of bites in 1 symbol. Just useful constant
    */
  val bitesInHex: Int = 4

  /**
    * A length of a checksum part of a key
    */
  val checksumLength: Int = checksumIndexes.size

  /**
    * A length of a domain part of a key
    */
  val domainLength: Int = domainIndexes.flatten.size

  /**
    *A Length of 1 key segment
    */
  val keySegmentLength = 4

  /**
    *A Length of 1 key window
    */
  val keySegmentStep = 4

  /**
    * This method parses a date entered by user to a LocalDate
    * @param date an entered date
    * @return a LocalDate object that represents an entered date
    */
  def parseDateToString(date: String): LocalDate = {
    val splitted = date.split("-")

    LocalDate.of(Integer.parseInt(splitted(2)), Integer.parseInt(splitted(1)), Integer.parseInt(splitted(0)))
  }

  /**
    * This method calculates a checksum as a CRC-16 value
    * @param data a data for which a checksum is generated
    * @return a new checksum
    */
  def crc16(data: Array[Byte]): String = {
    var crc = 0xFFFF

    for (i <- data.indices) {
      crc = ((crc >>> 8) | (crc << 8)) & 0xFFFF
      crc ^= (data(i) & 0xFF)
      crc ^= ((crc & 0xFF) >> 4)
      crc ^= (crc << 12) & 0xFFFF
      crc ^= ((crc & 0xFF) << 5) & 0xFFFF
    }

    String.format(s"%0${checksumLength}X", Integer.valueOf(crc))
  }

  /**
    * This method adds hyphens to some position in a key for better readability
    * @param target a key to delimit
    * @param segmentLength a length of 1 key segment
    * @param segmentStep a length of 1 key window
    * @return a key with hyphens
    */
  def addDelimiters(target: String, segmentLength: Int, segmentStep: Int, delimiter: String): String =
    target.sliding(segmentLength, segmentStep).mkString(delimiter)

}
