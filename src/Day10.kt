fun main() {
    val incomplete = mutableListOf<String>()
    val openC = listOf('(', '[', '{', '<')
    val closeC = listOf(')', ']', '}', '>')
    val valuesPart1 = listOf(3, 57, 1197, 25137)
    val valuesPart2 = listOf(1, 2, 3, 4)

    fun part1(input: List<String>): Int {
        var result = 0
        for (i in input) {
            val stack = ArrayDeque<Char>()
            var isIncomplete = true
            for (j in i.indices) {
                val x = i[j]
                if (x in openC) {
                    stack.add(x)
                    continue
                }

                val check = stack.removeLast()
                val idx = closeC.indexOf(x)
                if (openC.indexOf(check) != idx) {
                    result += valuesPart1[idx]
                    isIncomplete = false
                }
            }

            if (isIncomplete) {
                incomplete.add(i)
            }
        }
        return result
    }

    fun part2(): Long {
        val results = mutableListOf<Long>()
        for (i in incomplete) {
            var result: Long = 0
            val stack = ArrayDeque<Char>()
            for (j in i.indices) {
                val x = i[j]
                if (x in openC) {
                    stack.add(x)
                    continue
                }
                if (x in closeC) {
                    stack.removeLast()
                }
            }

            for (s in stack.reversed()) {
                result = result * 5 + valuesPart2[openC.indexOf(s)]
            }
            results.add(result)
        }

        results.sort()
        return results[results.size/2]
    }

    val input = readInput("Day10")
    println(part1(input))
    println(part2())
}
