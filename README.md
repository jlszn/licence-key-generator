# licence-key-generator

This program was created for a license key generation and validation.

## How to use
There is 2 variants: right from the code or generate a jar and use it
1. Run right from the code (all scripts lie in a root folder to have an access to build.sbt)
    1) To generate a key run `./generateKey.sh <domain> <date>`. Date must have the following format dd-mm-yyyy
    2) To validate a key run `./validateKey.sh <key>`. After it you will see a corresponding result as a message
    3) To test a program - run `sbt test`
2. Through a jar file
    1. Run `sbt assembly` in a root folder. It will generate a jar file in a ./jar folder. It will run tests too.
    2. Open a terminal in a ./jar folder.
    3. Use the same scripts as in the previous variant. Everything is the same.

## Structure

1. Util - consists of constants and common methods
2. App - an entrypoint
3. Decoder - an object that consists of methods used for decoding and validation of a key.
4. Encoder - an object that consists of methods used for key generation.
4. Messages - an object that contains different useful messages and strings 

#### Additional documentation

Read a specification in a `KeySpecification.docx`