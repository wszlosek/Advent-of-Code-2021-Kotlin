import java.lang.Integer.min

fun main() {
    var array: Array<IntArray> = arrayOf()
    var bigArray: Array<IntArray> = arrayOf()

    fun initArray(input: List<String>) {
        val column = input.size
        val row = input[0].length
        array = Array(column) { IntArray(row) }

        for (i in 0 until column) {
            for (j in 0 until row) {
                array[i][j] = input[i][j].digitToInt()
            }
        }
    }

    fun minCost(array: Array<IntArray>): Int {
        val column = array.size
        val row = array[0].size
        for (i in 1 until row) {
            array[i][0] += array[i - 1][0]
        }
        for (j in 1 until column) {
            array[0][j] += array[0][j - 1]
        }

        for (i in 1 until row) {
            for (j in 1 until column) {
                array[i][j] += min(array[i - 1][j], array[i][j - 1])
            }
        }
        return array[row - 1][column - 1] - array[0][0]
    }

    fun part1(input: List<String>): Int {
        initArray(input)
        return minCost(array)
    }

    fun initBigArray(input: List<String>) {
        bigArray = Array(5 * input.size) { IntArray(5 * input[0].length) }
        for (i in 0 until 5 * input[0].length) {
            for (j in 0 until 5 * input.size) {
                val dist = (i / array.size) + (j / array[0].size)
                var newV = array[i % array.size][j % array[0].size]
                repeat(dist) {
                    newV += 1
                    if (newV == 10) {
                        newV = 1
                    }
                }
                bigArray[i][j] = newV
            }
        }
    }

    fun part2(input: List<String>): Int {
        initArray(input)
        initBigArray(input)
        return minCost(bigArray)
    }

    val input = readInput("Day15")
    println(part1(input))
    println(part2(input))
}

