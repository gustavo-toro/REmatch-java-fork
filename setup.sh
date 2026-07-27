#!/bin/bash

set -euo pipefail

# Download javacpp
wget https://github.com/bytedeco/javacpp/releases/download/1.5.13/javacpp-platform-1.5.13-bin.zip

# Unzip
unzip javacpp-platform-1.5.13-bin.zip
rm javacpp-platform-1.5.13-bin.zip

# Store dependencies
mkdir -p javacpp/libs
cp javacpp-platform-1.5.13-bin/javacpp-linux-x86_64.jar javacpp/libs
cp javacpp-platform-1.5.13-bin/javacpp-platform.jar javacpp/libs
cp javacpp-platform-1.5.13-bin/javacpp.jar javacpp/libs

rm -r javacpp-platform-1.5.13-bin
