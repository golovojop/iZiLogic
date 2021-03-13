package s.yarlykov.izilogic.sort

class Quick(rawData: Array<Int>) : SortingAlgorithm(rawData) {

    override fun sort(low: Int, high : Int) {

        if(low >= high) return

        val pivotIndex = low + (high - low) / 2
        val pivot = rawData[pivotIndex]

        var i = low
        var j = high

        while (i < j) {
            while (rawData[i] < pivot) i++
            while (rawData[j] > pivot) j--
            swap(i, j)
        }

        sort(low, pivotIndex)
        sort(pivotIndex + 1, high)
    }

}

fun main() {

    Quick(arrayOf(20, 4, 81, 5, 35, 117, 99, 1, 72)).run {
        sort(0, rawData.lastIndex)
        print()
    }
}