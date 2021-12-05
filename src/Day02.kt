fun main() {

    val navigation: MutableMap<String, Int> = hashMapOf(
        "up" to 0, "down" to 0, "forward" to 0
    )

    fun increment(map: MutableMap<String, Int>, key: String, addValue: Int) {
        map.merge(key, addValue) { a: Int?, b: Int? -> a!! + b!! }
    }

    fun countPositions(input: List<String>) {
        for (i in input) {
            val posAndVal = i.split(" ")
            increment(navigation, posAndVal[0], posAndVal[1].toInt())
        }
    }

    fun part1(): Int {
        var horizontalPosition = 0
        var depth = 0

        horizontalPosition += navigation["forward"]!!
        depth += navigation["down"]!! - navigation["up"]!!

        return horizontalPosition * depth
    }

    fun part2(input: List<String>): Int {
        var horizontalPosition = 0
        var depth = 0
        var aim = 0

        for (i in input) {
            val posAndVal = i.split(" ")
            if (posAndVal[0] == "down") {
                aim += posAndVal[1].toInt()
            } else if (posAndVal[0] == "up") {
                aim -= posAndVal[1].toInt()
            } else if (posAndVal[0] == "forward") {
                horizontalPosition += posAndVal[1].toInt()
                depth += aim * posAndVal[1].toInt()
            }
        }

        return horizontalPosition * depth
    }

    val input = readInput("Day02")
    countPositions(input)
    println(part1())
    println(part2(input))
}