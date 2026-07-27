#!/bin/bash

set -euo pipefail

rm -rf build
mkdir build
cmake -Bbuild -DBUILD_JAR_LIBRARY=ON
cmake --build build
cp build/rematch.jar javacpp/libs
