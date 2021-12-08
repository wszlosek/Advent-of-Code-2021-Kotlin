fun main() {
    fun part1(input: List<String>): Int {
        var x = 0
        for (i in input) {
            val outputValues = i
                .split(" | ")
                .last()
                .split(" ")
                .filter {
                    it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7
                }
            x += outputValues.size
        }
        return x
    }

    val input = readInput("Day08")
    println(part1(input))
}