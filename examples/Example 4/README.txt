We used this example to test partial search within a statement.

(Note that the patterns were only used to test pattern matching, thus they won't necessarily make sense in terms of functional code.)

In this example we have two patterns, P1 and P2.

The first one will find a declaration of a variable a do while statement where, amongst other statements, @x will be decreased until @y is less than @z.

The second pattern will be used to test if the tool is able to recognise that, in this case, the variable being incremented should be @x. Since there isn't another while in the code it should not recognise it as a match.

Expected results:

P1 should match. P2 should not match.