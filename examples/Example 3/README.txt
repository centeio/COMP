We used this example to test exhaustive search with the while statement.

(Note that the patterns were only used to test pattern matching, thus they won't necessarily make sense in terms of functional code.)

In this example we have two patterns, P1 and P2. 

The first one will find a declaration of a variable of type int(@y) and an while statement where @y will start at @z and stop at @x, by incrementing @y.
Inside the while loop, there's an array access, being @w the array and @y the index, and the @y increment code fragment.Since the ignore keyword is prensent before and 
after the code fragment, this means the tool needs to search for these code fragments anywhere in the test code, as long as the for statement comes immediately after 
the declaration of the variable.

The second pattern will be used to test if the tool is able to recognise that, in this case, the index for the array access is @z instead of @y and since there's no 
code fragment like that in the test code, it should not recognise it as a match.

Expected results:

P1 should match. P2 should not match.
