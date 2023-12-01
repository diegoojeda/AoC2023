package day01

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int = input.map { strings ->
        val firstDigitPosition = strings.indexOfFirst { it.isDigit() }
        val lastDigitPosition = strings.indexOfLast { it.isDigit() }
        "${strings[firstDigitPosition]}${strings[lastDigitPosition]}"
    }.map { it.toInt() }.reduce { acc, next -> acc + next }

    fun part2(input: List<String>): Int {
        val mapped = input.map {
            var replacedString = it
            replacedString = replacedString.replace("one", "o1e")
            replacedString = replacedString.replace("two", "t2o")
            replacedString = replacedString.replace("three", "t3e")
            replacedString = replacedString.replace("four", "f4r")
            replacedString = replacedString.replace("five", "f5e")
            replacedString = replacedString.replace("six", "s6x")
            replacedString = replacedString.replace("seven", "s7n")
            replacedString = replacedString.replace("eight", "e8t")
            replacedString = replacedString.replace("nine", "n9e")
            replacedString
        }
        return part1(mapped)
    }

    val testInputPart1 = readInput("day01/Day01_test_part1")
    check(part1(testInputPart1) == 142)

    val inputPart1 = readInput("day01/Day01_part_1")
    part1(inputPart1).println()

    val testInputPart2 = readInput("day01/Day01_test_part_2")
    check(part2(testInputPart2) == 281)
    val inputPart2 = readInput("day01/Day01_part_2")
    part2(inputPart2).println()
}
