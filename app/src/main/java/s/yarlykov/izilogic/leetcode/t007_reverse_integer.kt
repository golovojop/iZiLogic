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
fun decomposeInt(map: SortedMap<Int, Int>, n: Int, power: Int) {
    // Base case
    if (n == 0) return

    val m = n / 10
    val r = n % 10

    map[power] = r
    return decomposeInt(map, m, power + 1)
}

/**
 * Обратная сборка
 *
 * key  value
 * [0]   3
 * [1]   2       ->   123
 * [2]   1
 */
fun composeInt(map: SortedMap<Int, Int>, power: Int): Int {
    return map[power]?.let { n ->
        (n * 10.pow(power)) + composeInt(map, power + 1)
    } ?: 0
}

/**
 * Сборка из списка. Индекс элемента в списке - это его степень 10.
 */
fun composeInt(list: List<Int>): Int {
    return list.foldIndexed(0) { i, acc, n  ->
        acc + (n * 10.pow(i))
    }

}

fun reverseInt(source: Int) : Int {

    if(source == Int.MIN_VALUE || source == Int.MAX_VALUE) return 0

    println("Original value: $source")

    val map = mutableMapOf<Int, Int>().toSortedMap()
    decomposeInt(map, source, 0)

    println("Decomposed map:")
    map.entries.forEach {
        println("${it.key}:${it.value}")
    }

    val revList = map.entries.map { it.value }.reversed()
    println("Compose back reversed list $revList: ${composeInt(revList)}")

    return composeInt(revList)
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
    val result = reverseInt(123456)
}