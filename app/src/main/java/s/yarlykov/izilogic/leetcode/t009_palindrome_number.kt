package s.yarlykov.izilogic.leetcode

/**
 * https://leetcode.com/problems/palindrome-number/
 *
 *
 */

fun isPalindromeNumber(num: Int): Boolean {

    if(num < 0) return false

    val array = ArrayList<Int>()

    var next = num
    var i = 0

    while (next != 0) {
        array.add(i++, next % 10)
        next /= 10
    }

    for (j in 0 until array.size / 2) {
        if (array[j] != array[array.size - j - 1]) return false
    }

    return true
}

fun main() {
    println(isPalindromeNumber(+1212121))
}