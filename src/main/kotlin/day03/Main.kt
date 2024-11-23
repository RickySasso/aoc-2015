package day03

import readLines
import readText

fun main() {
    part1()
    part2()
}

data class P(val x: Int, val y: Int) {
    fun up() = P(x + 1, y)
    fun down() = P(x - 1, y)
    fun left() = P(x, y - 1)
    fun right() = P(x, y + 1)
}

fun part1() {
    println("=== Part 1")
    val text = readText(3, "input.txt")

    var santa = P(0, 0)
    val set = mutableSetOf(santa)

    text.forEach {
        santa = when (it) {
            '^' -> santa.up()
            '>' -> santa.right()
            '<' -> santa.left()
            'v' -> santa.down()
            else -> TODO()
        }
        set.add(santa)
    }
    println(set.size)

}

fun part2() {
    println("=== Part 2")
    val text = readText(3, "input.txt")

    var santa = P(0, 0)
    var roboSanta = P(0, 0)
    val set = mutableSetOf(santa, roboSanta)

    var indicator = 0

    text.forEach {
        if (indicator == 0) {
            santa = when (it) {
                '^' -> santa.up()
                '>' -> santa.right()
                '<' -> santa.left()
                'v' -> santa.down()
                else -> TODO()
            }
            indicator = 1
            set.add(santa)
        }
        else {
            roboSanta = when (it) {
                '^' -> roboSanta.up()
                '>' -> roboSanta.right()
                '<' -> roboSanta.left()
                'v' -> roboSanta.down()
                else -> TODO()
            }
            indicator = 0
            set.add(roboSanta)
        }

    }
    println(set.size)

}