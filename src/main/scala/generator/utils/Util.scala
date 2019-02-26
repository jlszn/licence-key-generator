package generator.utils

/**
  * This utilitarian class contains useful constants and methods.
  */
object Util {

  /**
    * A sequence that represents places where a checksum is placed
    */
  val checkSumIndexes: Seq[Int] = Seq(0, 1, 2, 3)

  /**
    * A sequence that represents places where a domain is placed
    */
  val domainIndexes: Seq[Seq[Int]] = Seq(Seq(4, 5), Seq(7, 8, 9), Seq(12), Seq(16, 17, 18, 19))

  /**
    * A sequence that describes how to divide an entire key
    */
  val domainSplits: Seq[(Int, Int)] = domainIndexes
    .map(_.size)
    .scanLeft(0)(_ + _).tail
    .scanLeft((0,0))((prev, i) => (prev._2, i) ).tail

  /**
    * A sequence that represents places where an expiration date is placed
    * Order is sensible
    * 4 - MONTH
    * 8, 9 - DAY
    * 13, 14, 15 - YEAR
    */
  val dateIndexes: Seq[Seq[Int]] = Seq(Seq(13, 14, 15), Seq(6), Seq(10, 11))

  /**
    * An amount of bites in 1 symbol. Just useful constant
    */
  val bitesInHex: Int = 4

  /**
    * A length of a checksum part of a key
    */
  val checksumLength: Int = checkSumIndexes.size

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
  def addHyphens(target: String, segmentLength: Int, segmentStep: Int): String = {

    target.sliding(segmentLength, segmentStep).mkString("-")

  }

}
