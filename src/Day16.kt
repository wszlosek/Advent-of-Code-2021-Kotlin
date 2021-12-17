import java.math.BigInteger

sealed class Packet {
    var version: Int = 0
    open fun otherTypes(): Long {
        return 0
    }
}

data class Literal(val value: Long) : Packet() {
    override fun otherTypes(): Long {
        return value
    }
}

data class Operator(val typeId: Int, val subPackets: List<Packet>) : Packet() {
    override fun otherTypes(): Long {
        return when (typeId) {
            0 -> {
                var result = 0L
                subPackets.forEach { result += it.otherTypes() }
                return result
            }

            1 -> {
                var result = 1L
                subPackets.forEach { result *= it.otherTypes() }
                return result
            }

            2 -> {
                subPackets.minOf { it.otherTypes() }
            }

            3 -> {
                subPackets.maxOf { it.otherTypes() }
            }

            5 -> {
                return if (subPackets.first().otherTypes() > subPackets.last().otherTypes()) {
                    1
                } else {
                    0
                }
            }

            6 -> {
                return if (subPackets.first().otherTypes() < subPackets.last().otherTypes()) {
                    1
                } else {
                    0
                }
            }

            7 -> {
                return if (subPackets.first().otherTypes() == subPackets.last().otherTypes()) {
                    1
                } else {
                    0
                }
            }

            else -> return 0
        }
    }
}

class Parser(input: String) {
    private val bin: String
    var i = 0

    init {
        bin = BigInteger(input, 16).toString(2)
    }

    fun parse(): Packet {
        val packetVersion = bin.substring(i, i + 3).toInt(2)
        i += 3
        val packetTypeId = bin.substring(i, i + 3).toInt(2)
        i += 3

        return when (packetTypeId) {
            4 -> parseLiteral(packetVersion)
            else -> parseOperator(packetVersion, packetTypeId)
        }
    }

    private fun parseLiteral(version: Int): Literal {
        var result = ""
        do {
            val a = bin.substring(i, i + 5)
            result += a.substring(1)
            i += 5
        } while (a[0] == '1')

        val resultLiteral = Literal(result.toLong(2))
        resultLiteral.version = version

        return resultLiteral
    }

    private fun parseOperator(version: Int, typeId: Int): Operator {
        val lengthTypeId = bin.substring(i, i + 1)
        i += 1

        val subPackets = mutableListOf<Packet>()
        when (lengthTypeId) {
            "0" -> {
                val subPacketLength = bin.substring(i, i + 15).toInt(2)
                i += 15
                val max = i + subPacketLength
                while (i < max) {
                    subPackets.add(parse())
                }
            }

            "1" -> {
                val subPacketCount = bin.substring(i, i + 11).toInt(2)
                i += 11
                repeat(subPacketCount) {
                    subPackets.add(parse())
                }
            }
        }

        val resultOperator = Operator(typeId, subPackets)
        resultOperator.version = version
        return resultOperator
    }
}

fun main() {

    fun sumVersions(packet: Packet): Int {
        return when (packet) {
            is Literal -> packet.version
            is Operator -> {
                var result = packet.version
                packet.subPackets.forEach { result += sumVersions(it) }
                return result
            }
        }
    }

    fun part1(input: List<String>): Int {
        val packet = Parser(input[0]).parse()
        return sumVersions(packet)
    }


    fun part2(input: List<String>): Long {
        val packet = Parser(input[0]).parse()
        return packet.otherTypes()
    }

    val input = readInput("Day16")
    println(part1(input))
    println(part2(input))
}