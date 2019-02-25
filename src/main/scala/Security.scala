object Security {

  val checkSumIndexes = Seq(Seq(0, 1), Seq(11, 12))

  val domainIndexes = Seq(Seq(2, 3), Seq(5, 6, 7), Seq(10), Seq(16, 17, 18, 19))

  /**
    * 4 -MONTH
    * 8, 9 - DAY
    * 13, 14, 15 - YEAR
    */
  val dateIndexes = Seq(Seq(4), Seq(8, 9), Seq(13, 14, 15))

  val bitesInHex: Int = 4

  val checksumLength: Int = checkSumIndexes.size

}
