#!/bin/sh

h=$(hostname)

sbt "run validate-key $1 $h"
