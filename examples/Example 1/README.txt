We used this example to test exhaustive search with the if statement.

(Note that the patterns were only used to test pattern matching, thus they won't necessarily make sense in terms of functional code.)

In this example we have two patterns, P1 and P2. 

The first one will find a declaration of a variable of type int and an if statement where this same variable will be compared to a different one and, if true, it will 
subtract 1 and then add 1. Since the ignore keyword is prensent before, between and after the code fragment, this means the tool needs to search for these code 
fragments anywhere in the test code, as long as the if statement comes after the declaration of the variable, but not necessarily immediately after. 

The second pattern will be used to test if the tool is able to recognise that, in this case, the variable is being compared to itself in the if condition and since 
there's no code fragment like that in the test code, it should not recognise it as a match.

Expected results:

P1 should match. P2 should not match.

