import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun mutableIntToStringList (mut: MutableList<Int>): List<String>{
    val res: MutableList<String> = mutableListOf()
    for (m in mut) {
        res.add(m.toString())
    }
    return res.toList()
}

fun mutableToList (mut: MutableList<String>): List<String>{
    val res: MutableList<String> = mutableListOf()
    for (m in mut) {
        res.add(m)
    }
    return res.toList()
}

fun isAllUppercase(str: String): Boolean {
    for (c in str) {
        if (!c.isUpperCase()) {
            return false
        }
    }
    return true
}