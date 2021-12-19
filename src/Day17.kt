data class Position(var x: Int, var y: Int) {
    fun inArea(area: Area): Boolean {
        return x in area.x1..area.x2 && y in area.y1..area.y2
    }

    fun outside(area: Area): Boolean {
        return x > area.x2
    }
}

data class Area(var x1: Int, var x2: Int, var y1: Int, var y2: Int)

data class Probe(
    var currentPosition: Position,
    var targetArea: Area,
    var currentXChange: Int = 0,
    var currentYChange: Int = 0,
) {
    private var maxY = 0
    private var startX = currentPosition.x
    private var startY = currentPosition.y

    init {
        currentXChange = currentPosition.x
        currentYChange = currentPosition.y

        targetArea.x1 += currentPosition.x
        targetArea.x2 += currentPosition.x
        targetArea.y1 += currentPosition.y
        targetArea.y2 += currentPosition.y
    }

    private fun optArea() {
        targetArea.x1 -= startX
        targetArea.x2 -= startX
        targetArea.y1 -= startY
        targetArea.y2 -= startY
    }


    fun moveToArea(): Int {
        repeat(1000) {
            currentPosition.x += currentXChange
            currentPosition.y += currentYChange

            if (currentPosition.inArea(targetArea)) {
                optArea()
                return startY * (startY + 1) / 2
            }

            if (currentXChange > 0) {
                currentXChange -= 1
            } else if (currentXChange < 0) {
                currentXChange += 1
            }
            currentYChange -= 1
        }
        optArea()
        return Int.MIN_VALUE
    }
}

fun main() {

    val maxYList = mutableListOf<Int>()

    fun action(input: List<String>) {
        val cleanData = input.first().drop(13).split(", ")
        val x = cleanData.first().drop(2).split("..")
        val y = cleanData.last().drop(2).split("..")
        val area = Area(x.first().toInt(), x.last().toInt(), y.first().toInt(), y.last().toInt())

        for (i in -1000..area.x2) {
            for (j in -1000..1000) {
                val probe = Probe(Position(i, j), area)
                val maxY = probe.moveToArea()
                maxYList.add(maxY)
            }
        }
    }

    fun part1(): Int {
        return maxYList.maxOf { it }
    }

    fun part2(): Int {
        return maxYList.count { it > Int.MIN_VALUE }
    }

    val input = readInput("Day17")
    action(input)
    println(part1())
    println(part2())
}