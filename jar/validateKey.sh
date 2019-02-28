#!/bin/sh

h=$(hostname)

java -jar licence-key-encoded.jar validate-key $1 $h
