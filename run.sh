#!/bin/bash

# Compile Java files
echo "Compiling Java files..."
javac -d out $(find src -name "*.java")

# Run the main class
echo "Running application..."
java -cp out src.Main
