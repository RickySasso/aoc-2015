import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.io.path.readText

fun readLines(day: Int, name: String): List<String> {
    val dayStr = day.toString().padStart(2, '0')
    return Path("src/main/kotlin/day$dayStr/$name").readLines()
}

fun readText(day: Int, name: String): String {
    val dayStr = day.toString().padStart(2, '0')
    return Path("src/main/kotlin/day$dayStr/$name").readText()
}

fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')