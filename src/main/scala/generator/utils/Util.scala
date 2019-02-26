package generator.utils

object Util {

  val checkSumIndexes: Seq[Int] = Seq(0, 1, 2, 3)

  val domainIndexes: Seq[Seq[Int]] = Seq(Seq(4, 5), Seq(7, 8, 9), Seq(12), Seq(16, 17, 18, 19))

  val domainSplits: Seq[(Int, Int)] = domainIndexes
    .map(_.size)
    .scanLeft(0)(_ + _).tail
    .scanLeft((0,0))((prev, i) => (prev._2, i) ).tail

  /**
    * Order is sensible
    * 4 - MONTH
    * 8, 9 - DAY
    * 13, 14, 15 - YEAR
    */
  val dateIndexes: Seq[Seq[Int]] = Seq(Seq(13, 14, 15), Seq(6), Seq(10, 11))

  val bitesInHex: Int = 4

  val checksumLength: Int = checkSumIndexes.size

  val domainLength: Int = domainIndexes.flatten.size

  val crcLength = 4

  val keySegmentLength = 4

  val keySegmentStep = 4

  def crc16(data: Array[Byte]): String = {

    var crc = 0xFFFF

    for (i <- data.indices) {
      crc = ((crc >>> 8) | (crc << 8)) & 0xFFFF
      crc ^= (data(i) & 0xFF)
      crc ^= ((crc & 0xFF) >> 4)
      crc ^= (crc << 12) & 0xFFFF
      crc ^= ((crc & 0xFF) << 5) & 0xFFFF
    }
    String.format(s"%0${crcLength}X", Integer.valueOf(crc))

  }

  def addHyphens(target: String, segmentLength: Int, segmentStep: Int): String = {

    target.sliding(segmentLength, segmentStep).mkString("-")

  }

}
