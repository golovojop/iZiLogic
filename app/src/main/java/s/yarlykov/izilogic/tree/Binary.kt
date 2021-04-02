package s.yarlykov.izilogic.tree

import kotlin.math.max

/**
 * Класс бинарного дерева
 */
class Binary<T : Comparable<T>>(private val nodes: Set<Node<T>>) {

    var root: Node<T>? = null
        private set

    /**
     * Построить дерево из аргументов конструктора
     */
    init {
        nodes.forEach { add(it) }
    }

    /**
     * Если дерево пустое то становимся корнем. Иначе уходим в рекурсивную процедуру.
     */
    fun add(node: Node<T>): Boolean {
        return root?.let { addLoop(node, it) } ?: run { root = node; root != null }
    }

    fun remove(node: Node<T>): Boolean {
        return false
    }

    fun find(key: T): Node<T>? {
        return root?.let { findLoop(key, it) }
    }


    /**
     * Рекурсивно добавляем ноды в дерево.
     */
    private fun addLoop(node: Node<T>, parent: Node<T>): Boolean {
        return when {
            node < parent -> {
                parent.left?.let {
                    return addLoop(node, it)
                } ?: run { parent.left = node; true }
            }
            node > parent -> {
                parent.right?.let {
                    return addLoop(node, it)
                } ?: run { parent.right = node; true }
            }
            else -> false
        }
    }

    /**
     * Рекурсивно ищем ноду в дереве по ключу key.
     */
    private fun findLoop(key: T, next: Node<T>): Node<T>? {
        return when {
            key < next.key -> {
                next.left?.let { findLoop(key, it) }
            }
            key > next.key -> {
                next.right?.let { findLoop(key, it) }
            }
            else -> next
        }
    }

    fun print() {
        println(nodes)
        println("---------")
        nodes.forEach {
            println("[${it.key}] left: ${it.left?.key}, right: ${it.right?.key}")
        }
    }

    /**
     * Обход дерева по возрастанию ключей
     */
    fun inOrder(next: Node<T>?) {
        next?.let {
            inOrder(it.left)
            print(" ${it.key} ")
            inOrder(it.right)
        }
    }

    fun postOrder(next: Node<T>?) {
        next?.let {
            print(" ${it.key} ")
            postOrder(it.left)
            postOrder(it.right)
        }
    }


    /**
     * Поиск максимальной глубины
     */
    fun maxLevel(node : Node<T>?) : Int {
        node ?: return 0
        return max(maxLevel(node.left), maxLevel(node.right)) + 1
    }
}

fun main() {

//    val tree = Binary(manualValues)
//    tree.add(Node(100))
//    tree.print()
//
//    var search = 95
//    println("Search for $search. Result: ${tree.find(search)}")
//
//    search = 100
//    println("Search for $search. Result: ${tree.find(search)}")
//
//    println("\ntraversing inorder")
//    tree.inOrder(tree.root)
//
//    println("\ntraversing postorder")
//    tree.postOrder(tree.root)


    val tree2 = Binary(randomValues(7))
    tree2.print()
    println("max level = ${tree2.maxLevel(tree2.root)}")
}