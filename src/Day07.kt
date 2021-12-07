import kotlin.math.abs

fun main() {

    fun smallestFuelAmount(input: List<String>, addFunc: (Int) -> Long): Long {
        val values = input[0].split(",")
        var r1: Long = Long.MAX_VALUE
        for (i in 0..1000) {
            var sum: Long = 0
            for (v in values) {
                sum += addFunc(abs(v.toInt() - i))
            }
            if (sum < r1) {
                r1 = sum
            }
        }
        return r1
    }

    fun part1(input: List<String>): Long {
        val addition = { a: Int -> a.toLong() }
        return smallestFuelAmount(input, addition)
    }

    fun part2(input: List<String>): Long {
        val addition = { n: Int -> ((n + 1) * n / 2).toLong() }
        return smallestFuelAmount(input, addition)
    }

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}