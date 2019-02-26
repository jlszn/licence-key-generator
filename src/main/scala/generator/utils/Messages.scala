package generator.utils

object Messages {

  val delimiter = "-"

  val generateKeyCommand = "generate-key"

  val validateKeyCommand = "validate-key"

  val unknownCommand = "Unknown command"

  val invalidDomain = "Your domain is invalid!"

  val expiredDate = "Your key is expired!"

  val invalidKey = "Key is invalid!"

  val validKey = "Valid"

  val hashingAlgorithm = "SHA-512"

  val infoCommand = "info"

  val info =
    s"""
      |This application can be used for a key generation and validation.
      |It has 3 options:
      | 1) Generate key - Run as $generateKeyCommand <domain> <date>. Date has dd-mm-yyyy format
      | 2) Validate key - Run as $validateKeyCommand <key> <domain>
      | 3) Info - Run as $infoCommand
    """.stripMargin

}
