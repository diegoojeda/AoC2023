package day06

import println
import readInput
import kotlin.system.measureTimeMillis

data class Race(val time: Long, val distance: Long) {
    private fun calculateDistance(speed: Long, time: Long): Long = speed * time

    fun calculateResults(): Long = (0..time).count { calculateDistance(it, time - it) > distance }.toLong()
}

fun main() {
    fun parseLinePart1(line: String) =
        line.split(":").last().split(" ").map { it.trim() }.filter { it.isNotEmpty() }.map { it.toLong() }

    fun parseLinePart2(line: String) = line.split(":").last().replace(" ", "").trim().toLong()

    fun parseRaces(input: List<String>): List<Race> {
        val allTimes = input.first()
        val allDistances = input.last()
        val times = parseLinePart1(allTimes)
        val distances = parseLinePart1(allDistances)
        return times.mapIndexed { index, s -> Race(s, distances[index]) }
    }

    fun part1(input: List<String>): Long {
        var result: Long
        val timeMilis = measureTimeMillis {
            result = parseRaces(input).fold(1) { acc, next -> acc * next.calculateResults() }
        }
        println("Time Millis part 1: $timeMilis")
        return result
    }

    fun part2(input: List<String>): Long {
        var result: Long
        val timeMilis = measureTimeMillis {
            val allTimes = input.first()
            val allDistances = input.last()
            val time = parseLinePart2(allTimes)
            val distance = parseLinePart2(allDistances)
            val singleRace = Race(time, distance)
            result = singleRace.calculateResults();
        }
        println("Time Millis part 2: $timeMilis")
        return result
    }

    val testInputPart1 = readInput("day06/Day06_test_part1")
    val inputPart1 = readInput("day06/Day06_part_1")

    check(part1(testInputPart1) == 288L)
    check(part2(testInputPart1) == 71503L)
    part1(inputPart1).println()
    part2(inputPart1).println()
}
