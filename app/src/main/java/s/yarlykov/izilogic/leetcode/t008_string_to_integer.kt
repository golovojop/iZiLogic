package s.yarlykov.izilogic.leetcode

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 *
 * Сконвертить строку в число соблюдая заданные условия (см. url)
 */
fun strToInt(str: String): Int {

    val trimmedStr = str.trim()
    var startFrom = 0
    var sign = 1

    if (trimmedStr.isNotEmpty()) {
        if (trimmedStr[0] == '-') {
            sign = -1
            startFrom = 1
        }
        if (trimmedStr[0] == '+') startFrom = 1
    }

    var result = 0

    for (i in startFrom until trimmedStr.length) {
        if (trimmedStr[i] !in '0'..'9') break

        result *= 10
        val nextDigit = Character.getNumericValue(trimmedStr[i])

        if (sign > 0 && nextDigit > (Int.MAX_VALUE - result)) {
            return Int.MAX_VALUE
        }
        if (sign < 0 && nextDigit > (Int.MAX_VALUE - result)) {
            return Int.MIN_VALUE
        }

        result += nextDigit
    }

    return result * sign
}


fun main() {
    // MAX_VALUE: Int = 2147483647 (это 2^31 - 1)
    // MIN_VALUE: Int = -2147483648 (это -2^31)

    println("${strToInt("+00123a456")}")
    println("${strToInt("-2147483647")}")
}