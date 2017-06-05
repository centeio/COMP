#!/bin/bash

rm -r bin
find src/ -name "*.java" > sources.txt
mkdir bin
javac -d ./bin -cp ".:spoon2ast.jar:gson-2.8.0.jar:" @sources.txt
cp testsuite/Test.java bin/
rm sources.txt
