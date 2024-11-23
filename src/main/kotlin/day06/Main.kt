package day06

import readLines

fun main() {
    part1()
    part2()
}

fun part1() {
    println("=== Part 1")
    val lines = readLines(6, "input.txt")

    var arrayOfLights = Array(1000) {
        Array(1000) { 0 }
    }

    lines.forEach {
        val changed = it.replace("turn ", "")
        val splitString = changed.split(" ")

        val x1 = splitString[1].split(",")[0].toInt()
        val y1 = splitString[1].split(",")[1].toInt()
        val x2 = splitString[3].split(",")[0].toInt()
        val y2 = splitString[3].split(",")[1].toInt()

        if (splitString[0] == "on") {
            for (arrays in x1 .. x2) {
                for (lights in y1 .. y2) {
                    arrayOfLights[arrays][lights] = 1
                }
            }
        } else if (splitString[0] == "off") {
            for (arrays in x1 .. x2) {
                for (lights in y1 .. y2) {
                    arrayOfLights[arrays][lights] = 0
                }
            }
        } else {
            for (arrays in x1 .. x2) {
                for (lights in y1 .. y2) {
                    if (arrayOfLights[arrays][lights] == 0) {
                        arrayOfLights[arrays][lights] = 1
                    }else{
                        arrayOfLights[arrays][lights] = 0
                    }
                }
            }
        }
    }
    var lights = 0
    arrayOfLights.forEach {
        it.forEach {
            if (it == 1){
                lights++
            }
        }
    }
    println(lights)
}

fun part2() {
    println("=== Part 2")
    val lines = readLines(6, "input.txt")

    var arrayOfLights = Array(1000) {
        Array(1000) { 0 }
    }

    lines.forEach {
        val changed = it.replace("turn ", "")
        val splitString = changed.split(" ")
        val x1 = splitString[1].split(",")[0].toInt()
        val y1 = splitString[1].split(",")[1].toInt()
        val x2 = splitString[3].split(",")[0].toInt()
        val y2 = splitString[3].split(",")[1].toInt()

        if (splitString[0]== "on") {
            for (arrays in x1 .. x2) {
                for (lights in y1 .. y2) {
                    arrayOfLights[arrays][lights] += 1
                }
            }

        } else if (splitString[0]== "off") {
            for (arrays in x1 .. x2) {
                for (lights in y1 .. y2) {
                    if (arrayOfLights[arrays][lights] != 0) {
                        arrayOfLights[arrays][lights] -= 1
                    }
                }
            }
        } else {
            for (arrays in x1 .. x2) {
                for (lights in y1 .. y2) {
                    arrayOfLights[arrays][lights] += 2
                }
            }
        }
    }
    var lights = 0
    arrayOfLights.forEach {
        it.forEach {
            lights += it
        }
    }
    println(lights)
}
