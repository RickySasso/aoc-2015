package day02

import readLines

fun main() {
    part1()
    part2()
}

fun part1() {
    println("=== Part 1")
    val lines = readLines(2, "input.txt")

    var totalWrappingPaper = 0

    for (line in lines) {
        val (a, b, c) = line.split("x").map { it.toInt() }
        val s1 = a * b
        val s2 = b * c
        val s3 = a * c
        val s4 = minOf(s1, s2, s3)

        totalWrappingPaper += 2 * s1 + 2 * s2 + 2 * s3 + s4
    }
    println("The total amount of wrapping paper needed is: $totalWrappingPaper feet.")
}

fun part2() {
    println("=== Part 2")
    val lines = readLines(2, "input.txt")

    var totalWrappingPaper = 0

    for (line in lines) {
        val (a, b, c) = line.split("x").map { it.toInt() }
        val l1 = a + b + c - maxOf(a, b, c)
        totalWrappingPaper += l1 * 2 + (a * b * c)
    }
    println(totalWrappingPaper)
}