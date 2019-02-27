#!/bin/sh

h=$(hostname)

java -jar licence-key.jar validate-key $1 $h
