#!/bin/bash

set -eu
DIR="$(dirname "$(readlink -f "$0")")"
java -cp "$DIR/bin:$DIR/antlr-4.9.1-complete.jar" Compiler $@
