package s.yarlykov.izilogic.leetcode

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Given a string s, find the length of the longest substring without repeating characters.
 */

fun longestSubstring(sz : String = "abcabcdd") : String{

    val charSet = hashSetOf<Char>()
    var lastIndex = 0
    var longestSz = ""

    sz.forEachIndexed { i, ch ->

        if(charSet.contains(ch)) {
            if(i - lastIndex > longestSz.length) {
                longestSz = sz.substring(lastIndex, i)
            }
            lastIndex = i
            charSet.clear()
            charSet.add(ch)
        } else {
            charSet.add(ch)
        }
    }

    return longestSz
}

fun main() {

    val longestSz = longestSubstring()
    println("Length of the longest substring is ${longestSz.length}")
}