package generator
import java.io.ByteArrayOutputStream

import generator.utils.Messages
import org.scalatest.{Matchers, WordSpec}

class AppTest extends WordSpec with Matchers {

  def mockOut[T](t: => T): String = {

    val output = new ByteArrayOutputStream()

    Console.withOut(output)(t)

    output.toString

  }

  "App method main" must {
    "return error message if invalid command is passed" in {

      mockOut(App.main(Array("nocommand"))) shouldEqual Messages.unknownCommand + System.lineSeparator()

    }

    "return info message if info is passed" in {

      mockOut(App.main(Array(Messages.infoCommand))) shouldEqual Messages.info + System.lineSeparator()

    }

    "generate a key if generate is passed" in {

      val d = Messages.delimiter

      mockOut(App.main(Array(Messages.generateKeyCommand, "domain.com", "01-01-2020"))).trim should fullyMatch regex
        s"^([A-Z0-9]{4})$d([A-Z0-9]{4})$d([A-Z0-9]{4})$d([A-Z0-9]{4})$d([A-Z0-9]{4})$$"

    }

    "write valid if key is valid" in {

      mockOut(App.main(Array(Messages.validateKeyCommand, "E1A9-0018-D101-1833-149B", "golubnichiy"))) shouldEqual
        Messages.validKey + System.lineSeparator()

    }

    "write invalid domain if it's wrong" in {

      mockOut(App.main(Array(Messages.validateKeyCommand, "E1A9-0018-D101-1833-149B", "golub"))) shouldEqual
        Messages.invalidDomain + System.lineSeparator() + Messages.invalidKey + System.lineSeparator()

    }

    "write invalid key if it's wrong(too short)" in {

      mockOut(App.main(Array(Messages.validateKeyCommand, "123-123", "golub"))) shouldEqual
        Messages.invalidKey + System.lineSeparator()

    }

    "write invalid key if it's wrong(too long)" in {

      mockOut(App.main(Array(Messages.validateKeyCommand, "1234-1234-1234-1234-1234", "golub"))) shouldEqual
        Messages.invalidKey + System.lineSeparator()

    }

    "write expired date if it's wrong" in {

      mockOut(App.main(Array(Messages.validateKeyCommand, "DACD-00B8-D101-144B-149B", "golubnichiy"))) shouldEqual
        Messages.expiredDate + System.lineSeparator() + Messages.invalidKey + System.lineSeparator()

    }

    "write a domain error if a generate is called without any domain" in {

      mockOut(App.main(Array(Messages.generateKeyCommand))) shouldEqual Messages.emptyDomain + System.lineSeparator()

    }

    "write a date error if a generate is called without any date" in {

      mockOut(App.main(Array(Messages.generateKeyCommand, "domain.com"))) shouldEqual
        Messages.emptyDate + System.lineSeparator()

    }

    "write a key error if a validate is called without any key" in {

      mockOut(App.main(Array(Messages.validateKeyCommand))) shouldEqual Messages.emptyKey + System.lineSeparator()

    }

    "write a ket error if a validate is called without any domain" in {

      mockOut(App.main(Array(Messages.validateKeyCommand, "DACD-00B8-D101-144B-149B"))) shouldEqual
        Messages.emptyDomain + System.lineSeparator()

    }

  }

}
