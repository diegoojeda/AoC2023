package day03

import println
import readInput
import java.lang.Exception

data class Number(val value: Int, val row: Int, val startColumn: Int, val endColumn: Int)
data class Symbol(val value: Char, val row: Int, val column: Int)

fun isValidNumber(number: Number, symbol: Symbol): Boolean {
    return symbol.row in number.row - 1..number.row + 1 && symbol.column in number.startColumn..number.endColumn + 1
}

fun isValidNumberPart2(number: Number, symbol: Symbol): Boolean {
    return symbol.row in number.row - 1..number.row + 1 && symbol.column in number.startColumn..number.endColumn + 1 && symbol.value == '*'
}

fun main() {
    fun part1(input: List<String>): Int {
        val numbers = mutableListOf<Number>()
        val symbols = mutableListOf<Symbol>()
        input.forEachIndexed { index, row ->
            row.forEachIndexed { charIndex, char ->
                if (!char.isDigit() && char != '.')
                    symbols.add(Symbol(char, index, charIndex + 1))
            }
        }
        input.forEachIndexed { rowIndex, row ->
            var charIndex = 0
            while (charIndex < row.length) {
                if (row[charIndex].isDigit()) {
                    var numberString = ""
                    var numberParseIndex = charIndex
                    while (numberParseIndex < row.length && row[numberParseIndex].isDigit()) {
                        numberString += row[numberParseIndex]
                        numberParseIndex++
                    }
                    numbers.add(Number(numberString.toInt(), rowIndex, charIndex, numberParseIndex))
                    charIndex += numberString.length
                } else {
                    charIndex++
                }
            }
        }
        return numbers.fold(0) { acc, next ->
            val isValid = symbols.fold(false) { symbolAcc, symbolNext ->
                symbolAcc || isValidNumber(next, symbolNext)
            }
            if (isValid) acc + next.value else acc
        }
    }

    fun part2(input: List<String>): Int {
        val numbers = mutableListOf<Number>()
        val symbols = mutableListOf<Symbol>()
        input.forEachIndexed { index, row ->
            row.forEachIndexed { charIndex, char ->
                if (!char.isDigit() && char != '.')
                    symbols.add(Symbol(char, index, charIndex + 1))
            }
        }
        input.forEachIndexed { rowIndex, row ->
            var charIndex = 0
            while (charIndex < row.length) {
                if (row[charIndex].isDigit()) {
                    var numberString = ""
                    var numberParseIndex = charIndex
                    while (numberParseIndex < row.length && row[numberParseIndex].isDigit()) {
                        numberString += row[numberParseIndex]
                        numberParseIndex++
                    }
                    numbers.add(Number(numberString.toInt(), rowIndex, charIndex, numberParseIndex))
                    charIndex += numberString.length
                } else {
                    charIndex++
                }
            }
        }
        val result = numbers.fold(0) { acc, next ->
            val validSymbols = symbols.filter { symbol -> isValidNumberPart2(next, symbol) }
            if (validSymbols.size > 1) throw Exception("More than one valid symbol for same number")
            if (validSymbols.isEmpty()) return@fold acc + 0
            val validSymbol = validSymbols.first()
            val neighboursNumbers = numbers.filter { filterNumber ->
                validSymbol.row in filterNumber.row - 1..filterNumber.row + 1 && validSymbol.column in filterNumber.startColumn..filterNumber.endColumn + 1
            }
            val validNumbers = neighboursNumbers.filter { it != next }
            acc + if (validNumbers.isEmpty()) 0 else next.value * validNumbers.first().value
        }
        return result/2
    }

    val testInputPart1 = readInput("day03/Day03_test_part1")
    val inputPart1 = readInput("day03/Day03_part_1")

    check(part1(testInputPart1) == 4361)
    part1(inputPart1).println()
    check(part1(inputPart1) == 559667)

    part2(testInputPart1).println()
    check(part2(testInputPart1) == 467835)
    part2(inputPart1).println()
    check(part2(inputPart1) == 86841457)
}
