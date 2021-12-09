fun main() {
    var array: Array<IntArray> = arrayOf()
    var row = 0
    var column = 0
    var values = mutableListOf<Pair<Int, Int>>()

    fun initArray(input: List<String>) {
        column = input[0].length
        row = input.size
        array = Array(row) { IntArray(column) }

        for (i in 0 until row) {
            for (j in 0 until column) {
                array[i][j] = input[i][j].digitToInt()
            }
        }
    }

    fun fullJudge(i: Int, j: Int): Boolean {
        if (i == 0 && j == 0) {
            return array[i][j] < array[i + 1][j] && array[i][j] < array[i][j + 1]
        } else if (i == row - 1 && j == 0) {
            return array[i][j] < array[i - 1][j] && array[i][j] < array[i][j + 1]
        } else if (i == 0 && j == column - 1) {
            return array[i][j] < array[i][j - 1] && array[i][j] < array[i + 1][j]
        } else if (i == row - 1 && j == column - 1) {
            return array[i][j] < array[i][j - 1] && array[i][j] < array[i - 1][j]
        } else if (i == 0) {
            return array[i][j] < array[i][j - 1] && array[i][j] < array[i][j + 1]
                    && array[i][j] < array[i + 1][j]
        } else if (i == row - 1) {
            return array[i][j] < array[i][j - 1] && array[i][j] < array[i][j + 1]
                    && array[i][j] < array[i - 1][j]
        } else if (j == 0) {
            return array[i][j] < array[i - 1][j] && array[i][j] < array[i + 1][j]
                    && array[i][j] < array[i][j + 1]
        } else if (j == column - 1) {
            return array[i][j] < array[i - 1][j] && array[i][j] < array[i + 1][j]
                    && array[i][j] < array[i][j - 1]
        } else {
            return array[i][j] < array[i - 1][j] && array[i][j] < array[i + 1][j]
                    && array[i][j] < array[i][j - 1] && array[i][j] < array[i][j + 1]
        }
    }

    fun part1(): Int {
        var result = 0
        for (i in 0 until row) {
            for (j in 0 until column) {
                if (fullJudge(i, j)) {
                    result += 1 + array[i][j]
                }
            }
        }
        return result
    }

    fun basin(map: Array<IntArray>, i: Int, j: Int) {
        if (map[i][j] == 9 || values.contains(Pair(i, j))) {
            return
        }

        values.add(Pair(i, j))

        if (i != 0 && map[i - 1][j] > map[i][j]) {
            basin(map, i - 1, j)
        }
        if (j != 0 && map[i][j - 1] > map[i][j]) {
            basin(map, i, j - 1)
        }
        if (i != row - 1 && map[i + 1][j] > map[i][j]) {
            basin(map, i + 1, j)
        }
        if (j != column - 1 && map[i][j + 1] > map[i][j]) {
            basin(map, i, j + 1)
        }
    }

    fun part2(): Int {
        val basinsLen = mutableListOf<Int>()
        for (i in 0 until row) {
            for (j in 0 until column) {
                if ((i == 0 || array[i - 1][j] > array[i][j]) && (j == 0 || array[i][j - 1] > array[i][j])
                    && (i == row - 1 || array[i + 1][j] > array[i][j]) && (j == column - 1 || array[i][j + 1] > array[i][j])
                ) {
                    values = mutableListOf()
                    basin(array, i, j)
                    basinsLen.add(values.size)
                }
            }
        }

        var result = 1
        basinsLen
            .sorted()
            .filterIndexed { index, _ -> index >= basinsLen.size - 3 }
            .forEach { i -> result *= i }
        return result
    }

    val input = readInput("Day09")
    initArray(input)
    println(part1())
    println(part2())
}
