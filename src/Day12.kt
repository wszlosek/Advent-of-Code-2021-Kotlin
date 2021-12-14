fun main() {
    val graphMap = mutableMapOf<String, MutableSet<String>>()
    fun initMap(input: List<String>) {
        for (i in input) {
            val (a, b) = i.split("-")
            graphMap.getOrPut(a) { mutableSetOf() }.add(b)
            graphMap.getOrPut(b) { mutableSetOf() }.add(a)
        }
    }

    fun paths(
        graphMap: Map<String, Set<String>>,
        source: String,
        end: String,
        visited: MutableSet<String>,
        withExtraSum: Boolean = false
    ): Int {
        if (source == end) {
            return 1
        }

        var sum = 0
        visited.add(source)
        for (i in graphMap[source]!!) {
            if (i !in visited || isAllUppercase(i)) {
                sum += paths(graphMap, i, end, visited.toMutableSet(), withExtraSum)
            }
        }

        if (withExtraSum) {
            for (i in graphMap[source]!!) {
                if (i in visited && !isAllUppercase(i) && i !in listOf("start", "end")) {
                    sum += paths(graphMap, i, end, visited.toMutableSet())
                }
            }
        }

        visited.remove(source)
        return sum
    }

    fun part1(): Int {
        return paths(graphMap, "start", "end", mutableSetOf())
    }

    fun part2(): Int {
        return paths(graphMap, "start", "end", mutableSetOf(), true)
    }

    val input = readInput("Day12")
    initMap(input)
    println(part1())
    println(part2())
}
