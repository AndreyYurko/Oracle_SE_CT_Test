class BinaryTree(val value: Int) {

    var left: BinaryTree? = null
    var right: BinaryTree? = null
    private var size = 1

    fun size() = size

    // this function checks that all sub-nodes up to the leaves contain the same values and have the same structure
    fun contentsSimilar(lhv: BinaryTree?, rhv: BinaryTree?): Boolean {
        // Check if the value is the same. If both are null, we will get true
        if (lhv?.value != rhv?.value) return false
        // Check if the size are similar (for the speed). If both are null, we will get true
        if ((lhv?.size() ?: 0) != (rhv?.size() ?: 0)) return false
        // both are null
        if (lhv == null && rhv == null) return true
        return contentsSimilar(lhv?.left, rhv?.left) && contentsSimilar(lhv?.right, rhv?.right)
    }

    // You can consider that these methods are implemented
    // and you can use them if needed
//    abstract fun contains(value: Int): Boolean
//    abstract fun remove(value: Int): Boolean

    fun add(value: Int) {
        if (value > this.value) {
            if (this.right == null) {
                this.right = BinaryTree(value)
            } else {
                this.right!!.add(value)
            }
        } else if (value < this.value) {
            if (this.left == null) {
                this.left = BinaryTree(value)
            } else {
                this.left!!.add(value)
            }
        }
    }
}

fun main() {
    for (j in 0 until 10) {
        val tree1 = BinaryTree(1)
        val tree2 = BinaryTree(1)
        for (i in 0 until 1000) {
            val randomValue = (0..10000000).random()
            tree1.add(randomValue)
            tree2.add(randomValue)
            assert(tree1.contentsSimilar(tree1, tree2)) {
                "contentsSimilar is incorrect!"
            }
        }
    }
}