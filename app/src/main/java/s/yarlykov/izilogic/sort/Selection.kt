package s.yarlykov.izilogic.sort


/**
 * Сортировка выбором (выбором наименьшего).
 * Затратность: O(n) перестановок, однако количество сравнений O(n^n)
 */
class Selection(rawData: Array<Int>) : SortingAlgorithm(rawData) {

    companion object {
        private const val INDEX_INVALID = -1
    }

    /**
     * Проходим массив СЛЕВА НАПРАВО N-раз.
     * На каждом проходе ищем наименьший элемент и после прохода свапим его на
     * позицию с которой начинали эту итерацию. Постепенно слева направо
     * выстраивается отсортированная посдедовательность.
     */
    override fun sort(firstIndex: Int) {
        if (firstIndex == rawData.size) return

        var minValue = Int.MAX_VALUE
        var indexOfMinValue = INDEX_INVALID

        for (i in firstIndex..rawData.lastIndex) {
            iterations++
            if (rawData[i] < minValue) {
                indexOfMinValue = i
                minValue = rawData[i]
            }
        }

        if (indexOfMinValue != INDEX_INVALID) {
            swap(indexOfMinValue, firstIndex)
        }

        sort(firstIndex + 1)
    }
}

fun main() {

    Selection(arrayOf(20, 4, 81, 5, 117, 35, 99, 1, 72)).run {
        sort(0)
        print()
    }
}