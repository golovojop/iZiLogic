package s.yarlykov.izilogic.sort

abstract class SortingAlgorithm(val rawData: Array<Int>) {

    protected var iterations = 0
    private var swaps = 0

    abstract fun sort(index : Int)

    fun swap(from: Int, to: Int) {
        rawData[from] = rawData[to].also { rawData[to] = rawData[from] }
        swaps++
    }

    fun print() {
        println("${this::class.simpleName} Sort:")
        println("Array size ${rawData.size}")
        println("Iterations $iterations")
        println("Swaps $swaps")
        rawData.forEachIndexed { index, v ->
            print("$v")
            if (index < rawData.lastIndex) print(", ")
        }
    }
}