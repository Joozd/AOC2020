import utils.getInputForDay
import utils.getInputLinesForDay
import utils.getInputReaderForDay

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            /*
            val path = System.getProperty("user.dir")
            println("Working Directory = $path")
            */
            var keepGoing = true
            //while(keepGoing) {
                println("Please enter the day you want the solutions for (0 to exit)")
                when (readLine()) {
                    "0" -> keepGoing = false
                    "1" -> day1.Day1(getInputForDay(1)).run()
                    "2" -> day2.Day2(getInputLinesForDay(2)).run()
                    "3" -> day3.Day3(getInputForDay(3)).run()
                    else -> println("Invalid input\n")

                }
            //}
        }
    }
}