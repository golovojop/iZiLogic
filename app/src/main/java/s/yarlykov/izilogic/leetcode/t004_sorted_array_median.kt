package s.yarlykov.izilogic.leetcode

import s.yarlykov.izilogic.sort.mergeSort

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the
 * median of the two sorted arrays.
 *
 * NOTE: Median of a sorted array of size n is defined as the middle element when n
 * is odd and average of middle two elements when n is even.
 */
fun main() {
    val array1 = arrayOf(1, 2)
    val array2 = arrayOf(3, 4)

    val merge = mergeSort(array1.toIntArray(), array2.toIntArray())

    val size = merge.size

    val median = when {
        size == 1 -> merge[0].toFloat()
        size % 2 == 0 -> (merge[size / 2] + merge[size / 2 - 1]) / 2f
        else -> merge[size / 2].toFloat()
    }

    println("median of ${merge.toList()} is $median")
}