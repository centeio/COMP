#!/bin/bash

cd ../bin
java -cp ".:../spoon2ast.jar:../gson-2.8.0.jar:" main.PAT ../testsuite/Example4.txt

