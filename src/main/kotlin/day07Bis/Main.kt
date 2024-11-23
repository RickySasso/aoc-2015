package day07Bis

import readLines

fun main() {
    part1()
    part2()
}

fun part1() {
    println("=== Part 1")
    val lines = readLines(7, "input.txt")

    var codeList = lines.map { line -> Gate.parse(line) }

    val mem = Memory()

    while (codeList.isNotEmpty()) {
        codeList = mem.process(codeList, mem)
    }

    println(mem["a"])
}

fun part2() {
    println("=== Part 2")
    val lines = readLines(7, "input.txt")

    var codeList = lines.map { line -> Gate.parse(line) }

    val mem = Memory()

    val bSet = codeList.first {
        it is GSet && it.target == "b"
    } as GSet

    (bSet.source as Constant).value =  16076

    codeList = mem.process(codeList, mem)

    while (codeList.isNotEmpty()) {
        codeList = mem.process(codeList, mem)
    }

    println(mem["a"])
}
