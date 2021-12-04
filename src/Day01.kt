import kotlin.collections.*

fun main() {
    fun part1(input: List<String>): Int {
        var result = 0

        for (i in 1 until input.size) {
            if (input[i - 1] < input[i]) {
                result += 1
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0

        for (i in input.indices) {
            if (i > 2) {
                if (input[i - 3].toInt() < input[i].toInt()) {
                    result += 1
                }
            }
        }

        return result
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
