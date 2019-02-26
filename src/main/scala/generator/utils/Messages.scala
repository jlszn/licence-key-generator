package generator.utils

/**
  * An object that contains different messages and strings used in program
  */
object Messages {

  /**
    * Delimiter of a key
    */
  val delimiter = "-"

  /**
    * A generate key command
    */
  val generateKeyCommand = "generate-key"

  /**
    * A validate key command
    */
  val validateKeyCommand = "validate-key"

  /**
    * An unknown command message
    */
  val unknownCommand = "Unknown command"

  /**
    * An invalid domain message
    */
  val invalidDomain = "Your domain is invalid!"

  /**
    * An expired date message
    */
  val expiredDate = "Your key is expired!"

  /**
    * An invalid key message
    */
  val invalidKey = "Key is invalid!"

  /**
    * A valid key message
    */
  val validKey = "Valid"

  /**
    * A name of a hashing algorithm used inside the program
    */
  val hashingAlgorithm = "SHA-512"

  /**
    * An info command
    */
  val infoCommand = "info"

  /**
    * An info message
    */
  val info =
    s"""
      |This application can be used for a key generation and validation.
      |It has 3 options:
      | 1) Generate key - Run as $generateKeyCommand <domain> <date>. Date has dd-mm-yyyy format
      | 2) Validate key - Run as $validateKeyCommand <key> <domain>
      | 3) Info - Run as $infoCommand
    """.stripMargin

}
