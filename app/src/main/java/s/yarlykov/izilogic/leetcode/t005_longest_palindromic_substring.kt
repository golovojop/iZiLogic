package s.yarlykov.izilogic.leetcode

import s.yarlykov.izilogic.other.clearString
import s.yarlykov.izilogic.other.isPalindrome

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 * Given a string s, return the longest palindromic substring in s.
 *
 * NOTE: https://betterprogramming.pub/algorithms-101-palindromes-8a06ea97af86
 */

const val MIN_CONSTRAINT = 1
const val MAX_CONSTRAINT = 1000

var longestPalindrome = ""

fun findPalindrome(str: String) {
    val clearSz = str.clearString().take(MAX_CONSTRAINT)

    /**
     * Сначала сканируем всю исходную строку в поисках полидромов . Потом сканируем её подстроку
     * от индекса 1 и до конца. Потом следующую подстроку от индекса 2 и до конца. И т.д.
     */
    for (i in 0..clearSz.lastIndex) {

        // небольшая оптимизация, чтобы не делать лишние итерации
        if ((clearSz.lastIndex - i) < longestPalindrome.length) break

        for (j in (i + 1)..clearSz.length) {
            val subSz = clearSz.substring(i, j)

            if (subSz.isPalindrome && (subSz.length > longestPalindrome.length)) {
                longestPalindrome = subSz
            }
        }
    }
}

fun main() {
    findPalindrome("atrl ak.ka  la")
    println(longestPalindrome)
}