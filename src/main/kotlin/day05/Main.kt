package day05

import readLines

fun main() {

    part1()
    part2()
}

fun threeVowelCheck(text: String): Boolean {
    val x = text.groupBy { it }
        .map {
            it.key to it.value.size
        }
        .toMap()
        .withDefault { 0 }

    val count = x.getValue('a') + x.getValue('e') + x.getValue('i') + x.getValue('o') + x.getValue('u')
    return count > 2
}

fun doubleLetterCheck(text: String): Boolean {
    for (i in 0 until text.length - 1) {
        if (text[i] == text[i + 1]) {
            return true
        }
    }
    return false
}

fun notAllowedWordsCheck(text: String): Boolean {
    for (i in 0 until text.length - 1) {
        if ((text[i] == 'a' && text[i + 1] == 'b') ||
            (text[i] == 'c' && text[i + 1] == 'd') ||
            (text[i] == 'p' && text[i + 1] == 'q') ||
            (text[i] == 'x' && text[i + 1] == 'y')
        ) {
            return false
        }
    }
    return true
}


fun part1() {
    println("=== Part 1")
    val lines = readLines(5, "input.txt")

    var niceCounter = 0
    lines.forEach {
        if (threeVowelCheck(it) && doubleLetterCheck(it) && notAllowedWordsCheck(it)) {
            niceCounter++
        }
    }
    println("There are - $niceCounter - nice words.")
}

fun part2() {
    println("=== Part 2")
    val lines = readLines(5, "input.txt")

    var niceWordCounter = 0
    lines.forEach {
        if (doubleRepeat(it) && letterAfterLetter(it)) {
            niceWordCounter++
        }
    }
    println("There are - $niceWordCounter - nice words.")
}

fun letterAfterLetter(text: String): Boolean {
    for (i in 0 until text.length - 2) {
        if (text[i] == text[i + 2]) {
            return true
        }
    }
    return false
}

fun doubleRepeat(text: String): Boolean {
    for (i in 0 until text.length - 3) {
        val left = text.substring(i, i + 2)
        val right = text.substring(i + 2)
        if (left in right) {
            return true
        }
    }
    return false
}