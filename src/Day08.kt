fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        for (i in input) {
            val outputValues = i.split(" | ").last().split(" ")
            for (p in outputValues) {
                if (p.length == 2 || p.length == 3 || p.length == 4 || p.length == 7) {
                    result += 1
                }
            }
        }
        return result
    }

    val input = readInput("Day08")
    println(part1(input))
}