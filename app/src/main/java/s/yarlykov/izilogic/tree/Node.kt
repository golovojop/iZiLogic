package s.yarlykov.izilogic.tree

data class Node<T : Comparable<T>>(val key: T) : Comparable<Node<T>> {
    var left: Node<T>? = null
    var right: Node<T>? = null

    override fun toString(): String = "[$key]: l=${left?.key}, r=${right?.key}"

    override fun compareTo(other: Node<T>): Int {
        return when {
            this.key > other.key -> 1
            this.key < other.key -> -1
            else -> 0
        }
    }
}

/**
 * Рекурсивно ищем ноду в дереве.
 *
 * Receiver Node<T> - это текущая проверяемая нода.
 */
fun <T : Comparable<T>> Node<T>.findLoop(key: T): Node<T>? {
    return when {
        key < this.key -> {
            this.left?.findLoop(key)
        }
        key > this.key -> {
            this.right?.findLoop(key)
        }
        else -> this
    }
}

/**
 * Рекурсивно добавляем ноду <node> в дерево.
 *
 * Receiver Node<T> - это текущий потенциальный parent для добавляемой ноды.
 */
fun <T : Comparable<T>> Node<T>.addLoop(node: Node<T>): Boolean {
    return when {
        node < this -> {
            this.left?.addLoop(node) ?: run { this.left = node; true }
        }
        node > this -> {
            this.right?.addLoop(node) ?: run { this.right = node; true }
        }
        else -> false
    }
}