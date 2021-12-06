fun main() {

    fun actionOnMap(fishMap: Map<Int, Long>): Map<Int, Long> {
        val result = fishMap.mapKeys { (key, value) -> key - 1 }.toMutableMap()
        result[8] = result.getOrDefault(-1, 0)
        result[6] = result.getOrDefault(6, 0) + result.getOrDefault(-1, 0)
        result.remove(-1)
        return result
    }

    fun part1(input: List<String>, n: Int): Long {
        val fishAndTimers = input[0]
            .split(",")
            .map {it.toInt()}
            .groupingBy { it }
            .eachCount()
            .mapValues { (_, v) -> v.toLong() }

        var currMap = fishAndTimers
        for (i in 1..n) {
            currMap = actionOnMap(currMap)
        }
        return currMap.values.sum()
    }

    fun part2(input: List<String>): Long {
        return part1(input, 256)
    }

    val input = readInput("Day06")
    println(part1(input, 80))
    println(part2(input))
}
