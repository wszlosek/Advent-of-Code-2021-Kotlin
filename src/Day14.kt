fun main() {
    val mapRules = mutableMapOf<String, String>()
    var polymerTemplate = ""
    var polymerTemplateBuilder = StringBuilder()

    fun initOrders(input: List<String>) {
        polymerTemplate = input[0]
        for (i in 2 until input.size) {
            val x = input[i].split(" -> ")
            mapRules[x[0]] = x[1]
        }
        polymerTemplateBuilder = StringBuilder(polymerTemplate)
    }

    fun polymerGrowing(steps: Int): Int {
        repeat(steps) {
            var i = 0
            while (true) {
                if (i >= polymerTemplateBuilder.length - 1) {
                    break
                }
                val ruleKey = polymerTemplateBuilder[i].toString() + polymerTemplateBuilder[i + 1].toString()
                if (mapRules.containsKey(ruleKey)) {
                    polymerTemplateBuilder.insert(i + 1, mapRules[ruleKey])
                    i += 1
                }
                i += 1
            }
        }
        val countCharacters = polymerTemplateBuilder.toString().groupingBy { it }.eachCount()

        return (countCharacters.maxByOrNull { it.value }?.value!! - countCharacters.minByOrNull { it.value }?.value!!)
    }

    fun part1(input: List<String>): Int {
        initOrders(input)
        return polymerGrowing(10)
    }

    fun part2(input: List<String>): Int {
        initOrders(input)
        return polymerGrowing(40)
    }

    val input = readInput("Day14")
    println(part1(input))
    println(part2(input))
}