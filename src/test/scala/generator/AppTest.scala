package generator
import java.io.ByteArrayOutputStream

import generator.utils.Messages
import org.scalatest.{FlatSpec, Matchers}


class AppTest extends FlatSpec with Matchers {

  val validKey = "E1A9-0018-D101-1833-149B"

  def mockOut[T](t: => T): String = {

    val output = new ByteArrayOutputStream()

    Console.withOut(output)(t)

    output.toString

  }

  "App method main" should " return error message if invalid command was passed" in {

    mockOut(App.main(Array("nocommand"))) shouldEqual Messages.unknownCommand + System.lineSeparator()

  }

  "App method main" should " return info message if info was passed" in {

    mockOut(App.main(Array(Messages.infoCommand))) shouldEqual Messages.info + System.lineSeparator()

  }

  "App method main " should "generate a key if generate was passed" in {

    val d = Messages.delimiter

    mockOut(App.main(Array(Messages.generateKeyCommand, "domain.com", "01-01-2020"))).trim should fullyMatch regex
      s"^([A-Z0-9]{4})$d([A-Z0-9]{4})$d([A-Z0-9]{4})$d([A-Z0-9]{4})$d([A-Z0-9]{4})$$"

  }

  "App method main" should "write valid if key is valid" in {

    mockOut(App.main(Array(Messages.validateKeyCommand, validKey, "golubnichiy"))) shouldEqual
      Messages.validKey + System.lineSeparator()

  }

  "App method main" should "write invalid domain if it's wrong" in {

    mockOut(App.main(Array(Messages.validateKeyCommand, validKey, "golub"))) shouldEqual
    Messages.invalidDomain + System.lineSeparator() + Messages.invalidKey + System.lineSeparator()

  }

  "App method main" should "write invalid key if it's wrong(too short)" in {

    mockOut(App.main(Array(Messages.validateKeyCommand, "123-123", "golub"))) shouldEqual
    Messages.invalidKey + System.lineSeparator()

  }

  "App method main" should "write invalid key if it's wrong(too long)" in {

    mockOut(App.main(Array(Messages.validateKeyCommand, "1234-1234-1234-1234-1234", "golub"))) shouldEqual
      Messages.invalidKey + System.lineSeparator()

  }

  "App method mian" should "write expired date if it's wrong" in {

    mockOut(App.main(Array(Messages.validateKeyCommand, "DACD-00B8-D101-144B-149B", "golubnichiy"))) shouldEqual
      Messages.expiredDate + System.lineSeparator() + Messages.invalidKey + System.lineSeparator()

  }

}
