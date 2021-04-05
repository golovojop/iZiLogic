package s.yarlykov.izilogic.leetcode

import kotlin.math.max

/**
 * https://leetcode.com/problems/two-sum/
 *
 *  Given an array of integers nums and an integer target, return indices
 *  of the two numbers such that they add up to target. You may assume that
 *  each input would have exactly one solution, and you may not use the same
 *  element twice.
 *  You can return the answer in any order.
 */

val numbers = /*listOf(2, 7, 11, 15)*/ listOf(3, 2, 4)
const val target = /*9 */6

fun search(acc: Int, i: Int): Int {
    if (acc == target) return target
    if (acc > target || i >= numbers.size) return Int.MIN_VALUE

    val leftSearch = search(acc, i + 1)
    val rightSearch = search(acc + numbers[i], i + 1)

    if (rightSearch == target) println("$i")

    return max(leftSearch, rightSearch)
}

fun main() {
    search(0, 0)
}