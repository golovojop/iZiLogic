package s.yarlykov.izilogic.sort

/**
 * Merge Sort - это "склейка" двух заранее отсортированных массивов.
 */
fun mergeSort(array1: IntArray, array2: IntArray): IntArray {

    val merge = IntArray(array1.size + array2.size)

    var i = 0
    var j = 0
    var k = 0

    /**
     * Идем по массивам пока один из них не закончится
     */
    while (i < array1.size && j < array2.size) {
        merge[k++] = if (array1[i] <= array2[j]) {
            array1[i++]
        } else {
            array2[j++]
        }
    }

    /**
     * Теперь нужно определить массив, который НЕ закончился и его оставшиеся
     * элементы скопировать в результирующий массив.
     */
    if (i == array1.size && j < array2.size) {
        for (m in j until array2.size) merge[k++] = array2[m]
    }

    if (j == array2.size && i < array1.size) {
        for (m in i until array1.size) merge[k++] = array1[m]
    }

    return merge
}

fun main() {

    val array1 = arrayOf(11, 23, 31, 47, 90)
    val array2 = arrayOf(1, 37, 47, 51, 62)

    val r = mergeSort(array1.toIntArray(), array2.toIntArray())

    r.forEach { print("$it, ") }
}