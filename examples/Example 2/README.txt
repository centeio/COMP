We used this example to test exhaustive search with the for statement.

(Note that the patterns were only used to test pattern matching, thus they won't necessarily make sense in terms of functional code.)

In this example we have two patterns, P1 and P2. 

The first one will find a declaration of a variable of type int(@x) and an for statement where a counter(@z) will start in 0 and stop at @x by incrementing @z.
Inside the for loop, there is a function call with @x as an argument. Since the ignore keyword is prensent before, between and after the code fragment, this means the 
tool needs to search for these code fragments anywhere in the test code, as long as the for statement comes after the declaration of the variable, but not necessarily 
immediately after. 

The second pattern will be used to test if the tool is able to recognise that, in this case, the fucntion argument is @z and since there's no code fragment like that 
in the test code, it should not recognise it as a match.

Expected results:

P1 should match. P2 should not match.
