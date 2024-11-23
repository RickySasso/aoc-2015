package day04

import md5
import readText

fun main() {
    part1()
//    part2()
}


fun part1() {
    println("=== Part 1")
    val text = readText(4, "input.txt")

    val index = generateSequence(1) { it + 1 }
        .first {
            (text + it.toString()).md5().startsWith("00000")
        }
    println(index)
}

fun findNumWithLeadingZeros(key: String, leadingZeros: Int): Int {
    val targetPrefix = "0".repeat(leadingZeros) // Create a string of "00000"
    var num = 0

    while (true) {
        val hash = (key + num).md5()
        if (hash.startsWith(targetPrefix)) {
            return num
        }
        num++
    }
}


fun part2() {
    println("=== Part 2")
    val text = readText(4, "input.txt")

    val leadingZeros = 6
    val num = findNumWithLeadingZeros(text, leadingZeros)
    println("Part 1: $num")
    println("Hash: ${(text + num).md5()}")

}