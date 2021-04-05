package s.yarlykov.izilogic.sort

/**
 * Примеры того как можно инвертировать порядок элементов в Int массиве
 */

/**
 * Swap в одну строку без "использования третьей переменной"
 */
fun swapIntArray(array: ArrayList<Int>) {

    (0 until array.size / 2).forEachIndexed { i, _ ->
        array[i] = array[array.size - i - 1].also { array[array.size - i - 1] = array[i] }
    }

    println("array=${array}")
}

/**
 * Swap XOR'ом. Прицип такой:
 *
 * val tmp = arr[i].xor(arr[j])
 *
 * arr[i] = arr[j]
 * arr[j] = arr[j].xor(tmp)
 *
 */
fun swapIntArrayWithXor(array: ArrayList<Int>) {

    (0 until array.size / 2).forEachIndexed { i, _ ->
        array[i] = array[array.size - i - 1].also {
            array[array.size - i - 1] =
                array[array.size - i - 1].xor(array[i].xor(array[array.size - i - 1]))
        }
    }

    println("array=${array}")
}

fun main() {

    swapIntArray(arrayListOf(5, 4, 3, 2, 1))
    swapIntArrayWithXor(arrayListOf(7, 6, 5, 4, 3, 2, 1))

}