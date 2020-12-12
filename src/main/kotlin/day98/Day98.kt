package day98

import Solution // Contains functions for reading input file and timing how long it takes to get the answers
import java.io.File

/**
 * day 98 is for semi-unrelated doodling
 */
class Day98(day: Int): Solution(day) {
    // val extraInput = getExtraInputLinesForDay(dayNumber, "a")

    override val first: String
        get() = one()
    override val second: String
        get() = two()

    private fun one(): String{
        //opgave 18
        val keys = Array<Char?>(29){ null }.apply{
            set(7, 'Q')
            set(18, 'Y')
            set(22, '*')
            set(25, 'X')
        }
        val path = inputLines.joinToString("").map{it.toString().toInt()}
        val foundPath = (1..28).mapNotNull{ startPos ->
            var currentPos = startPos.also { if (keys[it] != null) return@mapNotNull null }
            val route = ArrayList<Int>()
            path.forEach {
                if (it == 0) route += currentPos else currentPos = move(currentPos, it) ?: return@mapNotNull null
            }
            route
        }
        return foundPath.toString().also{
            println((1..28).filter{keys[it] != null}.any {
                it in foundPath.flatten()})
        }
    }

    /**
     * @param currentPos: Position of current key
     * @param direction: Direction of next key
     * @return Position of next key, or null if illegal move
     */
    private fun move(key: Int, direction: Int): Int?{
        if ((key <= 4 && direction in listOf(5,6)) ||
                (key >= 25 && direction in listOf(2,3)) ||
                (key % 4 == 1 && direction == 1) ||
                (key % 4 == 0 && direction == 4) ||
                (key in listOf(5,13,21) && direction in listOf(2,6)) ||
                (key in listOf(4,12,20,28) && direction in listOf(3,5)))
            return null
        val oddLine = (key-1) / 4 % 2 == 1 // lines start numbering at 0
        return when(direction){
            1 -> key - 1
            4 -> key + 1
            2 -> if (oddLine) key + 3 else key + 4
            3 -> if (oddLine) key + 4 else key + 5
            5 -> if (oddLine) key - 4 else key - 3
            6 -> if (oddLine) key - 5 else key - 4
            0 -> key
            else -> error ("bad input")
        }
    }

    private fun six(): String {
        val possibleCharacters = inputLines.joinToString("").split("B")
        //return possibleCharacters.joinToString("") { it.toString() }.split("[, ]").map{ it.count { it == '[' }.toString() + it}.joinToString("\n")
        return possibleCharacters.joinToString("\n")
        //return possibleCharacters.groupingBy { it }.eachCount().toList().sortedByDescending { it.second }
        //    .joinToString("\n") { "${it.first.toString().padEnd(10, ' ')}: ${it.second}" }
    }

    private fun two(): String {
        return "Answer to question 2"
    }

    private fun removeRepeats(list: List<List<Byte>>): List<List<Byte>>{
        val result = ArrayList<List<Byte>>()
        var previous: List<Byte>? = null
        list.forEachIndexed { index, px ->
            if (index != list.lastIndex){
                if (px != previous) result += px
            }
            previous = px
        }
        return result
    }


    private fun grabThemColors(): String {
        val data = File("c:\\temp\\out2.raw").readBytes()
        val lines = data.toList().chunked(3).chunked(435)
        val usedLines = (0..16).map { (it * THICKNESS + THICKNESS/2).toInt() }.map{
            lines[it]
        }.map { pixel -> pixel.filter { it in usedColors }}
        val sequences = usedLines.map{removeRepeats(it)}
        return sequences.map { line ->
            line.map {
                when (it) {
                    RED -> "R"
                    GREEN -> "G"
                    BLUE -> "B"
                    WHITE -> ""
                    else -> "-"
                }
            }.joinToString("")
        }.joinToString("\n")
    }

    companion object{
        const val THICKNESS = 246/17f
        private val RED     = listOf<Byte>(-1, 0, 0)
        private val GREEN   = listOf<Byte>(0, -1, 0)
        private val BLUE    = listOf<Byte>(0, 0, -1)
        private val WHITE   = listOf<Byte>(-1,-1,-1)
        private val usedColors = listOf(RED, GREEN, BLUE, WHITE)

    }
}

