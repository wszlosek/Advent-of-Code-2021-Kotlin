fun main() {

    val range = 1400
    val array: Array<Array<String>> = Array(range) { Array(range) { "." } }
    val orders: MutableList<String> = mutableListOf()

    fun printArray(rangeX: Int = 1400, rangeY: Int = 1400) {
        for (i in 0 until rangeX) {
            for (j in 0 until rangeY) {
                print(array[i][j])
            }
            println()
        }
        println(orders)
    }

    fun initArray(input: List<String>) {
        for (i in input) {
            if (!i.contains("=") && i.length > 2) {
                val x = i.split(",")
                array[x[1].toInt()][x[0].toInt()] = "#"
            } else if (i.length > 2) {
                orders.add(i.split(" along ")[1])
            }
        }
    }

    fun foldingPaperX(numberLine: Int) {
        for (i in 0 until range) {
            for (j in 0 until range) {
                if (j > numberLine && array[i][j] == "#") {
                    array[i][numberLine - (j - numberLine)] = "#"
                    array[i][j] = "."
                }
            }
        }
    }

    fun foldingPaperY(numberLine: Int) {
        for (i in 0 until range) {
            for (j in 0 until range) {
                if (i > numberLine && array[i][j] == "#") {
                    array[numberLine - (i - numberLine)][j] = "#"
                    array[i][j] = "."
                }
            }
        }
    }

    fun countC(): Int {
        var result = 0
        for (i in 0 until range) {
            for (j in 0 until range) {
                if (array[i][j] == "#") {
                    result += 1
                }
            }
        }
        return result
    }

    fun part1(input: List<String>): Int {
        initArray(input)
        val ord = orders[0].split("=")
        if (ord[0] == "x") {
            foldingPaperX(ord[1].toInt())
        } else {
            foldingPaperY(ord[1].toInt())
        }

        return countC()
    }

    fun part2(input: List<String>) {
        orders.clear()
        initArray(input)
        for (i in orders) {
            val ord = i.split("=")
            if (ord[0] == "x") {
                foldingPaperX(ord[1].toInt())
            } else {
                foldingPaperY(ord[1].toInt())
            }
        }

        printArray(6, 39)
    }

    val input = readInput("Day13")
    println(part1(input))
    println(part2(input))
}
