import utils.Timer
import utils.getInputForDay
import utils.getInputLinesForDay
import java.time.Duration

abstract class Solution(day: Int) {
    // protected val inputString = getInputForDay(day)
    protected val inputLines = getInputLinesForDay(day)
    /**
     * Number of this day
     */
    protected val dayNumber: Int = day

    /**
     * In case groups are separated by two line-breaks:
     */
    protected val inputGroups: List<List<String>> by lazy { inputLines.joinToString("~").split("~~").map{it.split("~")} }

    /**
     * Answer for first question as String
     */
    protected abstract val first: String

    /**
     * Answer for second question as String
     */
    protected abstract val second: String

    private val timer = Timer()

    fun run(): Duration{
        timer.start()
        println("****************************************\nDay $dayNumber\n")
        println ("question 1:\n$first")
        val firstTime = timer.getElapsedTime()
        println("Elapsed time: ${firstTime.toMillis()} millis.\n")
        println ("question 2:\n$second")
        val elapsedTime = timer.getElapsedTime()
        println("Elapsed time for 2: ${(elapsedTime - firstTime).toMillis()} millis")
        println("Total time: ${elapsedTime.toMillis()} millis.\n****************************************\n")
        return elapsedTime
    }
}