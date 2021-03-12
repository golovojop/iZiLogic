package s.yarlykov.izilogic.sort

/**
 * Сортировка пузырьковая.
 * Затратность: O(n*n)
 */
class Bubble(rawData: Array<Int>) : SortingAlgorithm(rawData) {

    /**
     * Рекурсивно проходим N-раз по массиву перемещая очередной наибольший элемент вправо.
     */
    override fun sort(lastIndex: Int) {
        if (lastIndex == 0) return

        for (i in 0 until lastIndex) {
            iterations++
            if (rawData[i] > rawData[i + 1]) {
                swap(i, i + 1)
            }
        }
        sort(lastIndex - 1)
    }
}

fun main() {

    Bubble(arrayOf(20, 4, 81, 5, 117, 35, 99, 1, 72)).run {
        sort(rawData.lastIndex)
        print()
    }
}