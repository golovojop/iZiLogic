package s.yarlykov.izilogic.np

import kotlin.math.max


/**
 * Задача следующая. Иемеется надоб из нескольких элементов разной массы (например) и есть
 * рюкзак, который может выдержать некторый пороговый вес. Необходимо определить:
 * - наборы элементов, которые полностью заполнят рюкзак, не оставив свободного месте.
 * - набор/наборы элементов которые максимально заполнят рюкзак (отдельная реализация)
 *
 * Алгоритм следующий. Двигаемся по списку элементов и на каждом шаге рассматриваем два
 * варианта:
 * - этот элемент участвует.
 * - этот элемент не участвует.
 *
 * В результате получаем дерево, которое нужно пройти сверху вниз.
 */

// Например элементы с массами 1, 2, 3 и рюкзак вместительностью 4.
//
//                                  [1]
//                         0    /         \   +1
//                           /               \
//                        /                     \
//                     [2]                       [2]
//              0    /     \ 0+2               /    \
//                 /        \             1+0 /       \  1+2
//               /           \               /          \
//             [3]            [3]         [3]            [3]
//           0    3         2     5     1   ((4))      3     6
//
//  Найден один элемент.

class Knapsack(
    private val capacity: Int,
    private val items: List<Int>
) {

    /**
     * Функция должна сложить items в рюкзак заполнив его максимально.
     */
    fun pack() {
        packLoop(0, 0)
    }

    /**
     * @param i - индекс текущего элемента
     * @param acc - накопитель
     */
    private fun packLoop(i: Int, acc: Int): Int {
        // Перебор. Эта ветка не подходит. Всплываем.
        if (acc > capacity) return Int.MIN_VALUE
        // Дерево закончено или найден элемент, которым заполнили рюкзак. Всплываем.
        // NOTE: когда доходим до листа, то у нас либо полный рюкзак, либо не полный.
        // Перевеса быть не может, мы его скидываем в Int.MIN_VALUE.
        if (i > items.lastIndex || acc == capacity) return acc

        // Ветка слева. В ней наш вес равен 0.
        val leftTraversalAcc = packLoop(i + 1, acc + 0)

        // Ветка справа. В ней наш вес учитывается.
        val rightTraversalAcc = packLoop(i + 1, acc + items[i])

        /**
         * ВАЖНО: Результат поиска печатается сообща всеми элементами, которые попали
         * в рюкзак. А элемент попал в рюкзак ТОЛЬКО если СПРАВА ему сообщили, что
         * rightTraversalAcc == capacity. Сумма элемента плюсовалась к общей сумме только
         * в его правой ветке. Соответственно он за ней и следит.
         */
        if (rightTraversalAcc == capacity) {
            println("i=$i, value=${items[i]}")
        }

        /**
         * Этот return выполняется уже при всплытии когда известен конечный результат
         * по каждой из веток данного элемента. Напоминаю, что если был перелет, то
         * acc = Int.MIN_VALUE. Поэтому возвращаем наверх max из обеих веток. Он гарантированно
         * не больше capacity.
         */
        return max(leftTraversalAcc, rightTraversalAcc)
    }


    /**
     * Здесь тот же принцип, но печаем каждый набор отдельно.
     * Важный момент: то что рюкзак заполнен мы определяем на шаге i + 1 от элемента, который
     * последним попал в рюкзак. И в этом месте создается пустой список списков
     * ArrayList<ArrayList<Int>>. Каждый отдельный список будет представлять отдельный набор
     * участвовавших элементов. Теперь с шага i + 1 начинается всплытие и каждый последующий
     * элемент получая с ПРАВОЙ ветки непустой список списков добавляет себя во все его списки.
     * Как уже говорилось элемент участвует своей массой только по правой ветки, а значит
     * все списки для него валидны.
     */
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
    calculatePack()
    println("------------------")
    calculatePackWithHistory()
}

fun calculatePack() {
    val knapsack = Knapsack(10, listOf(1, 2, 3, 7))
    knapsack.pack()
}

fun calculatePackWithHistory() {

    val items = listOf(1, 2, 3, 4, 7, 5)
    val capacity = 11

    val knapsack2 = Knapsack(capacity, items)
    val result = knapsack2.packWithHistory()


    println("capacity=$capacity\n")

    result?.forEach { indices ->
        val values = indices.map { items[it] }

        values.forEachIndexed { i, v ->
            print("$v"); if (i < values.lastIndex) print("+")
        }
        println("=${values.sum()}")
    }
}