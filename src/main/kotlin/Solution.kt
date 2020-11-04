import utils.Timer

open class Solution {
    open fun first() = println("First part not yet done")
    open fun second() = println("Second part not yet done")

    private val timer = Timer()

    fun run(){
        println ("question 1:")
        timer.start()
        first()
        val firstTime = timer.getElapsedTime()
        println("Elapsed time: ${firstTime.toMillis()} millis")
        println ("question 2:")
        second()
        val elapsedTime = timer.getElapsedTime()
        println("Elapsed time for 2: ${(elapsedTime - firstTime).toMillis()} millis")
        println("Total time: ${elapsedTime.toMillis()} millis")
    }
}