/*

First idea - only use ===. Pros - this will work perfectly for equality function (will fulfil all requirements for
equality function). Cons - no info about the object itself, and that will obviously not work in the general case.

The second idea - build equals function ourselves, destructuring object until we will get primitive objects. This will
convert the object into some oriented graph, where each node is some object and its edges point to values in fields of 
that object. So, if we want to compare two objects, we will destructure both of them and go through a graph, comparing 
it. It is obviously fulfilling all requirements for the equals function.

Pros:
 - Now we have a function that can compare all contents of the object, even if there are some problems with 'equals'
   method

Cons:
 - Really heavy comparison, uses reflection too much, may take too much time for comparison (for example, if we want to
   compare objects with existing references ti themself).
 - In some cases, we still want to compare using references. For example, if two different data structures contain the
   same elements, it is not always means that these structures are equal. And what means that at some point, we must
   stop our destructuring. But how to understand there we should stop?

The third idea - use the second one, but somehow decide when we want to use references, and when we want to use custom
(or user written) 'equals' function.

Pros:
 - Great adjustability

Cons:
 - I have no idea how to decide that should be used. In general case, I think it's almost impossible to understand that
   equality function user wants to see in assertions for random objects.

 */