fun main() {
    var array: Array<IntArray> = arrayOf()

    fun initArray(input: List<String>) {
        array = Array(10 + 2) { IntArray(10 + 2) }
        for (i in 0..11) {
            for (j in 0..11) {
                array[i][j] = Int.MIN_VALUE
            }
        }
        for (i in 1..10) {
            for (j in 1..10) {
                array[i][j] = input[i - 1][j - 1].digitToInt()
            }
        }
    }

    fun flashPoint(): Pair<Int, Int> {
        for (i in 1..10) {
            for (j in 1..10) {
                if (array[i][j] > 9) {
                    return Pair(i, j)
                }
            }
        }
        return Pair(Int.MIN_VALUE, Int.MIN_VALUE)
    }

    fun incNeighbours(flashPoint: Pair<Int, Int>) {
        for (i in -1..1) {
            for (j in -1..1) {
                if (array[flashPoint.first + i][flashPoint.second + j] != 0) {
                    array[flashPoint.first + i][flashPoint.second + j] += 1
                }
            }
        }
    }

    fun flashBang(): Int {
        var result = 0
        var point: Pair<Int, Int>

        while (flashPoint().also { point = it } != Pair(Int.MIN_VALUE, Int.MIN_VALUE)) {
            incNeighbours(point)
            array[point.first][point.second] = 0
            result += 1
        }
        return result
    }

    fun incArray() {
        for (i in 1..10) {
            for (j in 1..10) {
                array[i][j] += 1
            }
        }
    }

    fun part1(steps: Int): Int {
        var result = 0
        repeat(steps) {
            incArray()
            result += flashBang()
        }
        return result
    }

    fun wholeArrayFlash(): Boolean {
        for (i in 1..10) {
            for (j in 1..10) {
                if (array[i][j] != 0) {
                    return false
                }
            }
        }
        return true
    }

    fun part2(steps: Int): Int {
        repeat(steps) { index ->
            incArray()
            flashBang()
            if (wholeArrayFlash()) {
                return index + 1
            }
        }
        return -1
    }

    val input = readInput("Day11")
    initArray(input)
    println(part1(100))
    initArray(input)
    println(part2(Int.MAX_VALUE))
}
