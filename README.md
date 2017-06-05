PROJECT TITLE: PAT (P18)
GROUP: G35
 
Student 1:
Name:Carolina Centeio Jorge
UP: up201403090
Grade: 19
Contribution: 28%
 
Student 2:
Name: Inês Proença
UP: up201404228
Grade: 19
Contribution: 28%
 
Student 3:
Name: Mónica Ariana Fernandes
UP: up201404789
Grade: 14
Contribution 16%
 
Student 4:
Name: Tiago Almeida
UP: up201305665
Grade: 19
Contribution: 28%
 
**SUMMARY: 
This project intends to develop a simple DSL for specifying code patterns and to identify the code sections in a program that matches the input patterns.
The DSL must be based on the programming language to search (e.g. Java for a Java Pattern Language), 
but with extensions to identify certain patterns ("@patternName").For instance, the @ character followed by an identifier represents a "named pattern".
It may appear anywhere in the program. The input DSL code can contain only small code sections.
 
**EXECUTE: Compile the application with compile.sh in the folder root.
Then, navigate to the testsuite folder and run the script for the intended example.
 
**DEALING WITH SYNTACTIC ERRORS: 
When a  syntactic error occurs, our tool shuts down.
A message indicating the exception that causes the error is shown to the user.
 
**SEMANTIC ANALYSIS: 
The @ character followed by an identifier represents a variable or a code fragment. 
The ignore keyword in the DSL forces the tool to ignore all code until the next pattern element is found. 
 
**INTERMEDIATE REPRESENTATIONS (IRs):
The tool takes the patterns DSL and generates java code from that.
After that, we use the spoon2ast library provided by the teachers to convert the java code into JSON format, which is then used to create an AST, using the GSON library.
 
**CODE GENERATION: 
Our tool generates Java code. 
 
**OVERVIEW: 
First we made a JAVA test code. Then we run that code through spoon2Ast, and with a parser we created our first tree. 
Then we created the patterns that we want to be able to find. These patterns were created in Java code (with JavaCC).
Then we passed this new code through spoon2Ast and with a parser we created our second tree.
After all we compared the trees with each other to find matchers and patterns. 
This comparison was done with Visitors. We have 2 types of visitors,
FindaPattern that visits the tree in order to find the node that we want, for example, IF. 
And then we run PatternMatcher to check if the IF elements are matching. Then we continue the process till the end of the tree. 
 
 
**TESTSUITE AND TEST INFRASTRUCTURE:
We created 5 files. Each file contains a  running script.
 
 **TASK DISTRIBUTION: 
Carolina - Generation of java code from patterns. 
Tiago - Parser for Java test File.
Inês e Ariana - Visitors.
 
 
**PROS: 
It is possible to find exact patterns in java code.
It is possible to find partial patterns in java code. 
The process of visitors (code matching) is done concurrently.
 
**CONS: 
Our tool can't run every type of code fragments.
It is a incremental process.
The time was not enough to implement everything.
