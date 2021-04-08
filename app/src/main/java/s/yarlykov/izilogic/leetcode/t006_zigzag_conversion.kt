package s.yarlykov.izilogic.leetcode

/**
 *
 * https://leetcode.com/problems/zigzag-conversion/
 *
 * Зигзагом вывести строку на экран. Алгоритм такой:
 * Сначала заполняем столбец, потом поднимаемся зюгой наверх. Потом снова столбец и снова
 * зюгой и т.д.
 */
//  Результат:
//
//  A.....A.....A.....A.....A
//  A....AA....AA....AA....AA
//  A...A.A...A.A...A.A...A..
//  A..A..A..A..A..A..A..A...
//  A.A...A.A...A.A...A.A....
//  AA....AA....AA....AA.....
//  A.....A.....A.....A......

fun zigZag(str: String, rows: Int): Array<CharArray> {
    require(rows >= 1)

    /**
     * Наверное величину capacity можно как-то иначе точно определить
     */
    val capacity = if (rows > 1) str.length / 2 else str.length
    val chars = Array(rows) { CharArray(capacity) { '.' } }

    if (rows == 1) {
        str.toCharArray().copyInto(chars[0])
        return chars
    }

    var isZigzagging = false
    var col = 0
    var row = -1

    str.forEachIndexed { i, ch ->
        // Двигаемся снизу вверх слева направо (зюгой)
        if (isZigzagging) {
            chars[--row][++col] = ch
            isZigzagging = row != 0
        }
        // Двигаемся вертикально сверху вниз
        else {
            chars[++row][col] = ch
            isZigzagging = row == chars.lastIndex
        }
    }

    return chars
}

fun main() {
    val chars = zigZag(String(CharArray(50){'A'}), 7)

    chars.forEach {
        println(String(it))
    }
}