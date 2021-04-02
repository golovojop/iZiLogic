package s.yarlykov.izilogic.tree

import kotlin.random.Random

/**
 * Генераторы значений
 */
val randomValues: Set<Node<Int>>
    get() = (List(5) { Random.nextInt(10, 99) }).map { Node(it) }.toSet()

fun randomValues(n: Int): Set<Node<Int>> =
    (List(n) { Random.nextInt(10, 99) }).map { Node(it) }.toSet()


val manualValues: Set<Node<Int>>
    get() = listOf(65, 30, 95, 17, 88).map { Node(it) }.toSet()
