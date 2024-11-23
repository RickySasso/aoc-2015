package day01

import readLines
import readText

fun main() {
    part1()
    part2()
}

fun part1() {
    println("=== Part 1")

    val input = readText(1, "input.txt")
    var floor = 0

    input.forEach {
        when (it) {
            '(' -> floor++
            ')' -> floor--
        }
    }

    println("Floor: $floor")
}

fun part2() {
    println("=== Part 2")

    val input = readText(1, "input.txt")
    var floor = 0
    var position = 0

    for (it in input) {
        when (it) {
            '(' -> floor++
            ')' -> floor--
        }
        position++
        if (floor == -1)
            break
    }

    println("Floor: $floor")
    println("Pos: $position")
}