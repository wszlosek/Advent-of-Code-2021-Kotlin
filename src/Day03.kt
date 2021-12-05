fun main() {

    fun removeWherePositionEquals(input: MutableList<String>, index: Int, ifInIndexRemove: Char): MutableList<String> {
        val result = mutableListOf<String>()
        for (s in input) {
            if (input.size > 1 && s[index] != ifInIndexRemove) {
                result.add(s)
            } else if (input.size == 1) {
                result.add(s)
                break
            }
        }
        return result
    }

    fun part1(input: List<String>): Int {
        val s1 = StringBuilder()
        val s2 = StringBuilder()
        var n0 = 0
        var n1 = 0

        for (i in 0 until input[0].length) {
            for (j in input.indices) {
                if (input[j][i] == '0') {
                    n0 += 1
                } else {
                    n1 += 1
                }
            }

            if (n0 > n1) {
                s1.append("0")
                s2.append("1")
            } else {
                s1.append("1")
                s2.append("0")
            }
            n0 = 0
            n1 = 0
        }

        return s1.toString().toInt(2) * s2.toString().toInt(2)
    }

    fun oxygen(input: MutableList<String>): Int {
        var inputCopy = input
        var n0 = 0
        var n1 = 0

        for (i in 0 until inputCopy[0].length) {
            for (j in inputCopy.indices) {
                if (inputCopy[j][i] == '0') {
                    n0 += 1
                } else {
                    n1 += 1
                }
            }

            if (n0 > n1) {
                inputCopy = removeWherePositionEquals(inputCopy, i, '1')
            } else {
                inputCopy = removeWherePositionEquals(inputCopy, i, '0')
            }
            n0 = 0
            n1 = 0
        }
        return inputCopy[0].toInt(2)
    }

    fun co2(input: MutableList<String>): Int {
        var inputCopy = input
        var n0 = 0
        var n1 = 0

        for (i in 0 until inputCopy[0].length) {
            for (j in inputCopy.indices) {
                if (inputCopy[j][i] == '0') {
                    n0 += 1
                } else {
                    n1 += 1
                }
            }

            if (n0 > n1) {
                inputCopy = removeWherePositionEquals(inputCopy, i, '0')
            } else {
                inputCopy = removeWherePositionEquals(inputCopy, i, '1')
            }
            n0 = 0
            n1 = 0
        }
        return inputCopy[0].toInt(2)
    }

    fun part2(input: MutableList<String>): Int {
        return oxygen(input) * co2(input)
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input.toMutableList()))
}