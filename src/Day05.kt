import kotlin.math.abs
import kotlin.math.sign

fun main() {
    data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int)
    val points: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()

    fun increment(map: MutableMap<Pair<Int, Int>, Int>, key: Pair<Int, Int>) {
        map.merge(key, 1) { a: Int?, b: Int? -> a!! + b!! }
    }

    fun addPoints(startPointFirst: Int, startPointSecond: Int,
                    endPointFirst: Int, endPointSecond: Int) {
        for (i in minOf(startPointFirst, endPointFirst)..maxOf(startPointFirst, endPointFirst)) {
            for (j in minOf(startPointSecond, endPointSecond)..maxOf(startPointSecond, endPointSecond)) {
                increment(points, Pair(i, j))
            }
        }
    }

    fun part1(input: List<String>): Int {
        var result = 0
        for (line in input) {
            val lineSplit = line.split(",", " -> ")
            if (lineSplit[0].toInt() == lineSplit[2].toInt() || lineSplit[1].toInt() == lineSplit[3].toInt()) {
                addPoints(lineSplit[0].toInt(), lineSplit[1].toInt(), lineSplit[2].toInt(), lineSplit[3].toInt())
            }
        }
        for (i in points) {
            if (i.value >= 2) {
                result += 1
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        val a = input.map { l ->
            val s = l.split(" -> ")
            val (x1, y1) = s[0].split(",").map { it.toInt() }
            val (x2, y2) = s[1].split(",").map { it.toInt() }
            Line(x1, y1, x2, y2)
        }
        val mx = a.maxOf { l -> maxOf(l.x1, l.x2) }
        val my = a.maxOf { l -> maxOf(l.y1, l.y2) }
        val points2 = Array(mx + 1) { IntArray(my + 1) }
        for (l in a) {
            val dx = (l.x2 - l.x1).sign
            val dy = (l.y2 - l.y1).sign
            for (t in 0..maxOf(abs(l.x1 - l.x2), abs(l.y1 - l.y2)))
                points2[l.x1 + t * dx][l.y1 + t * dy]+= 1
        }

        for (x in 0..mx) {
            for (y in 0..my) {
                if (points2[x][y] > 1) {
                    result += 1
                }
            }
        }
        return result
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
