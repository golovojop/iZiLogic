package s.yarlykov.izilogic.np

import kotlin.math.max

class Knapsack(
    private val capacity: Int,
    private val items: List<Int>
) {

    /**
     * Функция должна сложить items в рюкзак заполнив его максимально.
     */
    fun pack(): List<Int> {
        val selection = Array(items.size) { -1 }

        pack(0, 0, selection)
        return selection.asList()
    }

    private fun pack(i: Int, acc: Int, selection: Array<Int>): Int {
        if (acc > capacity) return Int.MIN_VALUE
        if (i > items.lastIndex || acc == capacity) return acc

        // Не участвуем в выборе
        val passiveRes = pack(i + 1, acc + 0, selection)

        // Участвуем в выборе и проверяем результат
        val activeRes = pack(i + 1, acc + items[i], selection)

        if (activeRes == capacity) {
            println ("i=$i, value=${items[i]}")
            selection[i] = i
        }
        return max(passiveRes, activeRes)
    }
}


fun main() {
    val knapsack = Knapsack(11, listOf(1, 2, 3, 4, 7, 5))
//    val knapsack = Knapsack(4, listOf(1, 2, 3))
    val list = knapsack.pack()
}