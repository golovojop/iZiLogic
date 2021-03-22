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

    /**
     * @param i - индекс текущего элемента
     * @param acc - накопитель
     * @param selection - должен содержать список выбранных элементов. Не работает в этой версии.
     */
    private fun pack(i: Int, acc: Int, selection: Array<Int>): Int {
        if (acc > capacity) return Int.MIN_VALUE
        if (i > items.lastIndex || acc == capacity) return acc

        // Не участвуем в выборе
        val passiveRes = pack(i + 1, acc + 0, selection)

        // Участвуем в выборе и проверяем результат
        val activeRes = pack(i + 1, acc + items[i], selection)

        if (activeRes == capacity) {
            println("i=$i, value=${items[i]}")
            selection[i] = i
        }
        return max(passiveRes, activeRes)
    }

    fun packWithHistory(): ArrayList<ArrayList<Int>>? {
        return packWithHistoryLoop(i = 0, acc = 0)
    }

    private fun packWithHistoryLoop(
        i: Int,
        acc: Int
    ): ArrayList<ArrayList<Int>>? {

        when {
            // Результат достигнут. Создать новый список для записи тех, кто участвовал в
            // суммировании общего результата. Список будет заполняться по мере всплытия.
            (acc == capacity) -> {
                return ArrayList<ArrayList<Int>>().apply { add(ArrayList()) }
            }

            // Перелёт: дальше нет смысла продолжать. Или прошли всё, но результат плохой.
            (acc > capacity) || (i > items.lastIndex) -> return null
        }

        // Обход ветки где я НЕ участвую
        val leftTraversal = packWithHistoryLoop(i + 1, acc + 0)
        // Обход ветки где я УЧАСТВУЮ (acc + items[i])
        val rightTraversal = packWithHistoryLoop(i + 1, acc + items[i])

        // Поместить себя во все подмассивы результата правой ветки
        rightTraversal?.forEach {
            it.add(i)
        }

        // Теперь нужно смержить подмассивы обеих веток в один список подмассивов
        return rightTraversal?.also { right -> leftTraversal?.let(right::addAll) } ?: leftTraversal
    }
}

fun main() {
    val knapsack = Knapsack(11, listOf(1, 2, 3, 4, 7, 5))
//    val knapsack = Knapsack(4, listOf(1, 2, 3))

//    val list = knapsack.pack()

    val r = knapsack.packWithHistory()

    r?.forEach {
        println(it.toString())
    }
}