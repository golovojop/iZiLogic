package s.yarlykov.izilogic.sort

/**
 * Сортировка пузырьковая.
 * Затратность: O(n*n)
 */
class Bubble(val rawData: Array<Int>) {

    /**
     * Рекурсивно проходим N-раз по массиву перемещая очередной наибольший элемент вправо.
     */
    fun sort(lastIndex: Int) {
        if (lastIndex == 0) return

        for (i in 0 until lastIndex) {
            if (rawData[i] > rawData[i + 1]) {
                swap(i, i + 1)
            }
        }
        sort(lastIndex - 1)
    }

    private fun swap(from: Int, to: Int) {
        rawData[from] = rawData[to].also { rawData[to] = rawData[from] }
    }

    fun print() {
        rawData.forEachIndexed { index, v ->
            print("$v")
            if (index < rawData.lastIndex) print(", ")
        }
    }
}

fun main() {

    Bubble(arrayOf(20, 4, 81, 5, 117, 35, 99, 1, 72)).run {
        sort(rawData.lastIndex)
        print()
    }
}