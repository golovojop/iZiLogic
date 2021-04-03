package s.yarlykov.izilogic.leetcode

import kotlin.math.pow

/**
 * https://leetcode.com/problems/add-two-numbers/
 *
 * You are given two non-empty linked lists representing
 * two non-negative integers. The digits are stored in reverse order,
 * and each of their nodes contains a single digit. Add the two numbers
 * and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except
 * the number 0 itself.
 */

val a342 = listOf(2, 4, 3) // Это запись числа '342'
val b465 = listOf(5, 6, 4) // Это запись числа '465'

/**
 * Превратить список Int'ов, которые представляют собой цифры трехзначного положительного
 * числа, записанные в обратном порядке, в это самое трехзначное число.
 */
fun List<Int>.listToNum(): Int {
    return mapIndexed { index, n -> n * 10.toFloat().pow(index).toInt() }.sum()
}

fun main() {

    var overhead = 0

    /**
     * Обычное сложение столбиком.
     * overhead - для учета переноса единицы в следующий разряд.
     */
    val number = a342.zip(b465) { a, b ->
        var sum = a + b + overhead

        if (sum >= 10) {
            overhead = 1
            sum -= 10
        } else {
            overhead = 0
        }

        sum

    }.listToNum()

    println("$number")
}