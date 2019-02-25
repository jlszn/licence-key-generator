package key_generator

import java.time.Instant

object Generator {


  /**
   *
   * @param domain domain name, used in key generation and verification
   * @param date key expiration date, used in key generation
   * @return outputs generated licence key
   */
  def generate(domain: String, date: Instant): Unit = ???

}
