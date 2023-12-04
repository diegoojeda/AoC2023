package day04

import println
import readInput
import kotlin.math.*

data class Card(val id: Int, val winningNumbers: List<Int>, val playedNumbers: List<Int>) {
    fun calculateWinningNumbersAmount(): Int {
        return winningNumbers.toSet().intersect(playedNumbers.toSet()).count()
    }

    fun calculatePoints(): Int {
        return if (calculateWinningNumbersAmount() == 0) 0
        else 2.0.pow((calculateWinningNumbersAmount() - 1).toDouble()).toInt()
    }
}

fun main() {
    fun extractNumbers(winningNumbersString: String) =
        winningNumbersString.split(" ").map { it.trim() }.filterNot { it.isEmpty() }.map { it.toInt() }

    fun processCards(input: List<String>) = input.map { inputString ->
        val (cardIdString, values) = inputString.split(":")
        val cardId = cardIdString.split(" ").last().toInt()
        val (winningNumbersString, playedNumbersString) = values.split("|")
        val winningNumbers = extractNumbers(winningNumbersString)
        val playedNumbers = extractNumbers(playedNumbersString)
        Card(cardId, winningNumbers, playedNumbers)
    }

    fun part1(input: List<String>): Int {
        return processCards(input).fold(0) { acc, next ->
            acc + next.calculatePoints()
        }
    }

    fun part2(input: List<String>): Int {
        val originalCards = processCards(input)
        val mutableCards = originalCards.toMutableList()
        var index = 0
        while(index < mutableCards.size){
            val card = mutableCards[index]
            val winningNumbersAmount = card.calculateWinningNumbersAmount()
            if (winningNumbersAmount > 0)
                mutableCards.addAll(originalCards.subList(card.id, card.id + winningNumbersAmount))
            index++
        }
        return mutableCards.size
    }

    val testInputPart1 = readInput("day04/Day04_test_part1")
    val inputPart1 = readInput("day04/Day04_part_1")

    check(part1(testInputPart1) == 13)
    part1(inputPart1).println()
    check(part2(testInputPart1) == 30)
    part2(inputPart1).println()
}
