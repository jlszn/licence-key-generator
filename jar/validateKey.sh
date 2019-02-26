#!/bin/sh

h=$(hostname)

java -jar license-key.jar validate-key $1 $h
