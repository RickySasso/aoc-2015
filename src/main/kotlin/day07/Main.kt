package day07

import readLines
import java.util.*

fun main() {
    part1()
    part2()
}

val memory = mutableMapOf<String, Int>()

fun findValueOf(wire: String): Int? {
    return memory[wire]
}

var newLines = ArrayDeque<String>()

fun circuit(it: String): Boolean {
    val splitted = it.split(" ")
    when {
        splitted[1] == "->" -> {
            if (isInteger(splitted[0])) {
                memory[splitted[2]] = splitted[0].toInt()
                return true
            }
            else if (memory.containsKey(splitted[0])) {
                memory[splitted[2]] = memory[splitted[0]]!!
                return true
            }
            else{
                newLines.add(it)
                return false
            }
        }

        splitted[1] == "AND" -> {
            if (isInteger(splitted[0]) && isInteger(splitted[2])) {
                memory[splitted[4]] = splitted[0].toInt() and splitted[2].toInt()
                return true
            }
            else if (memory.containsKey(splitted[0]) && memory.containsKey(splitted[2])) {
                memory[splitted[4]] = memory[splitted[0]]!! and memory[splitted[2]]!!
                return true
            }
            else if (memory.containsKey(splitted[0]) && isInteger(splitted[2])) {
                memory[splitted[4]] = memory[splitted[0]]!! and splitted[2].toInt()
                return true
            }
            else if (isInteger(splitted[0]) && memory.containsKey(splitted[2])) {
                memory[splitted[4]] = splitted[0].toInt() and memory[splitted[2]]!!
                return true
            }
            else{
                newLines.add(it)
                return false
            }
        }

        splitted[1] == "OR" -> {
            if (isInteger(splitted[0]) && isInteger(splitted[2])) {
                memory[splitted[4]] = splitted[0].toInt() or splitted[2].toInt()
                return true
            }
            else if (memory.containsKey(splitted[0]) && memory.containsKey(splitted[2])) {
                memory[splitted[4]] = memory[splitted[0]]!! or memory[splitted[2]]!!
                return true
            }
            else if (memory.containsKey(splitted[0]) && isInteger(splitted[2])) {
                memory[splitted[4]] = memory[splitted[0]]!! or splitted[2].toInt()
                return true
            }
            else if (isInteger(splitted[0]) && memory.containsKey(splitted[2])) {
                memory[splitted[4]] = splitted[0].toInt() or memory[splitted[2]]!!
                return true
            }
            else{
                newLines.add(it)
                return false
            }
        }

        splitted[0] == "NOT" && memory.containsKey(splitted[1]) -> {
            memory[splitted[3]] = (memory[splitted[1]]!!).inv()
            return true
        }

        splitted[1] == "LSHIFT" && memory.containsKey(splitted[0]) -> {
            memory[splitted[4]] = memory[splitted[0]]!! shl splitted[2].toInt()
            return true
        }

        splitted[1] == "RSHIFT" && memory.containsKey(splitted[0]) -> {
            memory[splitted[4]] = memory[splitted[0]]!! shr splitted[2].toInt()
            return true
        }

        else -> {
            newLines.add(it)
            return false
        }
    }
}

fun isInteger(s: String): Boolean {
    val x = s.toIntOrNull()
    return x != null
}


fun part1() {
    println("=== Part 1")
    val lines = readLines(7, "input.txt")

    lines.forEach {
        circuit(it)
    }

    do {
        val currentQueue = ArrayDeque(newLines)
        newLines.clear()

        for (line in currentQueue) {
            circuit(line)
        }
    } while (newLines.isNotEmpty())

    val output = findValueOf("a")
    println(output)

}


fun part2() {
    println("=== Part 2")
    val lines = readLines(7, "input.txt")

    memory.clear()

    lines.forEach {
        if (it.endsWith("-> b")){
            memory["b"] = 16076
        }
        else {
            circuit(it)
        }
    }

    do {
        val currentQueue = ArrayDeque(newLines)
        newLines.clear()

        for (line in currentQueue) {
            circuit(line)
        }
    } while (newLines.isNotEmpty())

    val output = findValueOf("a")
    println(output)
}
