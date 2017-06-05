We used this example to test parcial search in the whole code block.

(Note that the patterns were only used to test pattern matching, thus they won't necessarily make sense in terms of functional code.)

In this example we have two patterns, P1 and P2. 

The first one will find a declarations of a variable of type int(@x) and a for statement where a counter(@z) will start in 0 and stop at @x by incrementing @z.
Inside the for loop, there must be a statement calling a fuction with @x as a parameter. 

The second pattern will be used to test if the tool is able to find every for loop in the code with a counter named i starting in 0 .

Because this is parcial there can be code between every statement of the pattern

Expected results:

P1 should match on time. P2 should match 3 times.
