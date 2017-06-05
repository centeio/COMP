We used this example to test parcial search in the whole code block.

(Note that the patterns were only used to test pattern matching, thus they won't necessarily make sense in terms of functional code.)

In this example we have two patterns, P1 and P2. 

The first one will find two declarations, one  of a variable of type int(@x) and another of an int array(@z) and an for statement where a counter(i) will start in 0 and stop at @x by incrementing i.
Inside the for loop, there must be a statement filling the ith position of the array (@z) with @x+1. 

The second pattern will be used to test if the tool is able to find every for loop in the code with a counter named i starting in 0 .

Because this is parcial there can be code between every statement of the pattern

Expected results:

P1 should match on time. P2 should match 3 times.
