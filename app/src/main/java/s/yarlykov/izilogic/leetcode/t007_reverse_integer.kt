package s.yarlykov.izilogic.leetcode

import java.util.*
import kotlin.math.pow

/**
 * Возведение в степень
 */
fun Int.pow(p: Int): Int = (this.toFloat().pow(p)).toInt()

/**
 * Функция раскладывает положительное целое на составляющие и сохраняет каждый
 * компонент в Map'е по степени 10. Например число '123' превращается в:
 *
 * key  value
 * [0]   3
 * [1]   2
 * [2]   1
 */
fun decomposeIntRecur(map: SortedMap<Int, Int>, n: Int, power: Int) {
    // Base case
    if (n == 0) return

    val m = n / 10
    val r = n % 10

    map[power] = r
    return decomposeIntRecur(map, m, power + 1)
}

/**
 * Обратная сборка (рекурсивно)
 *
 * key  value
 * [0]   3
 * [1]   2       ->   123
 * [2]   1
 */
fun composeIntRecur(map: SortedMap<Int, Int>, power: Int): Int {
    return map[power]?.let { n ->
        (n * 10.pow(power)) + composeIntRecur(map, power + 1)
    } ?: 0
}

/**
 * Сборка из списка в цикле. Индекс элемента в списке - это его степень 10.
 */
fun composeIntLoop(list: List<Int>): Int {
    return list.foldIndexed(0) { index, acc, n  ->

        // TODO: Проверить, что вписываемся в диапазон Int.MIN_VALUE <= N <= Int.MAX_VALUE
        if(n != 0 && (acc > Int.MAX_VALUE/10 || acc < Int.MIN_VALUE/10)) return 0

        acc + (n * 10.pow(index))
    }
}

/**
 * Сложны способ с импользованием сторонних структур для разборки и сборки
 */
fun reverseIntHard(source: Int) : Int {
    val map = mutableMapOf<Int, Int>().toSortedMap()
    decomposeIntRecur(map, source, 0)

    val revList = map.entries.map { it.value }.reversed()
    return composeIntLoop(revList)
}

/**
 * Самый простой вариант. Здесь число инвертируется в цикле без дополнительных структур.
 */
fun reverseIntSimple(source : Int) : Int {
    var src = source
    var result = 0

    while(src != 0) {

        // TODO: Проверить, что вписываемся в диапазон Int.MIN_VALUE <= result <= Int.MAX_VALUE
        if(result > Int.MAX_VALUE/10 || result < Int.MIN_VALUE/10) return 0

        val remainder = src % 10
        result = result * 10 + remainder
        src /= 10
    }

    return result
}

/**
 * min:  (-2^31);   -2147483648; 0x8000 0000; 1000 ....
 * max:  (2^31 - 1); 2147483647; 0x7FFF FFFF; 0111 ....
 *
 * (-1) = 0xFFFFF FFFFF
 *
 * Трабла в том, что например число 2147483647 нельзя реверсировать, потому что
 * 7 млрд не вписывается в 2^31-1. Для таких чисел нужно возвращать 0.
 */

fun main() {
//    reverseIntHard(/*123456*/Int.MAX_VALUE - 1_000_000_000)

    val result = reverseIntSimple(1201)
    println("result=$result")

}