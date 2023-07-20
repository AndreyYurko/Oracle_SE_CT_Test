/*
First of all, we need to decide that we mean by "equality". If we look in the Kotlin documentation, method equals
represents structural equality, and must fulfil some requirements:
 - x.equals(x) always true
 - x.equals(y) <=> y.equals(x)
 - x.equals(y) && y.equals(z) => x.equals(z)
 - consistency: multiple x.equals(y) should return same result (if there is no modifications)
 - if x is not null, x.equals(null) should return false

In the examples below I will try to introduce some possible problems with 'equals' method because of Kotlin language
problems, human errors or misunderstanding of that equals means.
*/

// This is a silly example, but the main point of this one is poor writing of function equals. It is clear that this
// implementation is not fulfilling the requirements provided in the documentation, but it is possible because
// mistakes in code are common.
class Example1 {
    override fun equals(other: Any?): Boolean {
        return !super.equals(other)
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

fun test1() {
    val example1 = Example1()
    println(example1 == example1)
}

// This is just an example of a not-nullable value that is null. If we try to use == everything will work.
class HolderForExample2<T> {
    val nullValue: T = run {
        nullValue2
    }
    private val nullValue2: T = run {
        nullValue
    }
}

fun test2() {
    val example21 = HolderForExample2<String>().nullValue
    val example22 = "Something"
    println(example21.equals(example22))
}

// And here is an example that can be found in documentation - the difference between equals and ==. One compares
// doubles using IEEE 754 Standard, and the other one compares the exact values. This makes sense because the method
// that uses == is not fulfilling the third requirement for equals function. This is an example of a different
// understanding what equality means.
fun test3() {
    val example31: Double = -0.0
    val example32: Double = 0.0
    val example33: Double = 0.0
    println(example31.equals(example32))
    println(example32.equals(example33))
}

class Example4(val value: Int)

// And the last example - a problem with understanding what 'equals' means. If we don't override the 'equals' method,
// we will still get a comparison using references, not the contents of the value.
fun test4() {
    val example41 = Example4(10)
    val example42 = Example4(10)
    val example43 = example41
    println(example41 == example42)
    println(example41 == example43)
}

// But I think in general it is okay to use equals on random objects. If the developers know that they do when the
// implementation of the equals function will work as intended. It will check if the objects are equal in the way they
// should be equal.

fun main() {
    // test1()
    // test2()
    // test3()
    test4()
}