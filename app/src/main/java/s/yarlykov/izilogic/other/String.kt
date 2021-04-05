package s.yarlykov.izilogic.other

import s.yarlykov.izilogic.leetcode.MIN_CONSTRAINT

/**
 * Удалить из строки все НЕАЛФАВИТНЫЕ и НЕЦИФРОВЫЕ символы.
 */
fun String.clearString(): String {
    val regEx = Regex("[^A-Za-z0-9]")
    return regEx.replace(this, "")
}

/**
 * Проверить, что строка есть Palindromic
 */
val String.isPalindrome: Boolean
    get() {
        require(length >= MIN_CONSTRAINT)

        for (i in 0 until length / 2) {
            if (this[i] != this[length - 1 - i]) {
                return false
            }
        }
        return true
    }