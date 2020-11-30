import utils.Timer
import utils.getInputForDay
import utils.getInputLinesForDay

abstract class Solution(day: Int) {
    protected val inputString: String by lazy { getInputForDay(day) }
    protected val inputLines: List<String>  by lazy { getInputLinesForDay(day) }
    /**
     * Number of this day
     */
    protected val dayNumber: Int = day

    /**
     * Answer for first question as String
     */
    protected abstract val first: String

    /**
     * Answer for second question as String
     */
    protected abstract val second: String

    private val timer = Timer()

    fun run(){
        timer.start()
        println("****************************************\nDay $dayNumber\n")
        println ("question 1:\n$first")
        val firstTime = timer.getElapsedTime()
        println("Elapsed time: ${firstTime.toMillis()} millis.\n")
        println ("question 2:\n$second")
        val elapsedTime = timer.getElapsedTime()
        println("Elapsed time for 2: ${(elapsedTime - firstTime).toMillis()} millis")
        println("Total time: ${elapsedTime.toMillis()} millis.\n****************************************\n")
    }
}