package day02

import println
import readInput

const val MAX_RED_CUBES = 12;
const val MAX_GREEN_CUBES = 13;
const val MAX_BLUE_CUBES = 14;

fun main() {

    fun isValid(color: String, count: Int): Boolean {
        return when (color) {
            "red" -> count <= MAX_RED_CUBES
            "green" -> count <= MAX_GREEN_CUBES
            "blue" -> count <= MAX_BLUE_CUBES
            else -> true
        }
    }

    fun mapEachColor(colorAndCount: String): Boolean {
        println(colorAndCount)
        val (count, color) = colorAndCount.trim().split(" ")
        println("count: $count, color $color")
        return isValid(color, count.toInt())
    }

    fun mapEachGame(it: String): Boolean {
        return it.split(",").fold(true) { acc, next ->
            acc && mapEachColor(next)
        }
    }

    fun part1(input: List<String>): Int {
        return input.fold(0) { acc, it ->
            val (gameId, gameContent) = it.split(":")
            val eachGame = gameContent.split(";")
            val isValid = eachGame.fold(true) { acc2, next ->
                val result = mapEachGame(next)
                acc2 && result
            }
            acc + if (isValid) gameId.split(" ")[1].toInt() else 0
        }
    }

    fun part2(input: List<String>): Int = input.map { inputMapped ->
        val gameContents = inputMapped.split(":")[1]
        val gameContentsSplitted = gameContents.split(";")
        val allContents = gameContentsSplitted.flatMap { f -> f.split(",") }
        println(allContents)
        allContents
                .map { it.trim().split(" ") }
                .map { it.last() to it.first().toInt() }
                .sortedBy { it.second }
                .toMap()
                .map { it.value }
                .fold(1) { acc, next ->
                    acc * next
                }
    }.reduce { acc, next -> acc + next }

    val testInputPart1 = readInput("day02/Day02_test_part1")
    val inputPart1 = readInput("day02/Day02_part_1")

    check(part1(testInputPart1) == 8)
    part1(inputPart1).println()

    check(part2(testInputPart1) == 2286)
    part2(inputPart1).println()
}
