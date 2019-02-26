package key_generator

object Util {

  val crcLength = 4

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

}
