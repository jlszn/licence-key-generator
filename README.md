# licence-key-generator

This program was created for a license key generation and validation.

## How to use

1) To generate a key run `./generateKey.sh <domain> <date>`. Date must have the following format dd-mm-yyyy
2) To validate a key run `./validateKey.sh <key>`. After it you will see corresponding result as a message
3) To test a program - run `sbt test`

## Structure

1. Util - consists of constants and common methods
2. App - an entrypoint
3. Decoder - an object that consists of methods used for decoding and validation of a key.
4. Encoder - an object that consists of methods used for key generation.
4. Messages - an object that contains different useful messages and strings 

#### Additional documentation

Read a specification in a `KeySpecification.docx`