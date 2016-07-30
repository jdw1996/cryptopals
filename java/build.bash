#!/usr/bin/env bash


# Create build directory if it doesn't already exist
mkdir build 2> /dev/null

# Compile all *.java files
javac -sourcepath src/ -d build src/**/*.java

# Copy all text files into build/
find src/ -name "*.txt" -exec cp {} build/ \;
