package s.yarlykov.izilogic.leetcode

fun strToInt(str: String): Int {

    val clearStr = str.trim()
    var startFrom = 0
    var sign = 1

    if (clearStr.isNotEmpty()) {
        if (clearStr[0] == '-') {
            sign = -1
            startFrom = 1
        }
        if (clearStr[0] == '+') startFrom = 1
    }

    var result = 0

    for(i in startFrom until clearStr.length) {
        if (clearStr.first() !in '0'..'9') break

        result *= 10
        result += clearStr[i].toInt()
    }

    return result * sign
}


fun main() {
    println("${strToInt("123")}")
}