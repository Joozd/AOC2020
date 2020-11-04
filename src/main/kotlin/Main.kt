import utils.getInputForDay
import utils.getInputLinesForDay

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Please enter the day you want the solutions for")
            when (readLine()) {
                "1" -> day1.Day1(getInputForDay(1)).run()
                "2" -> day2.Day2(getInputLinesForDay(2)).run()
                "3" -> day3.Day3(getInputForDay(3)).run()
                "4" -> day4.Day4(getInputForDay(4)).run()
                "5" -> day5.Day5(getInputLinesForDay(5)).run()
                "6" -> day6.Day6(getInputLinesForDay(6)).run()
                "7" -> day7.Day7(getInputLinesForDay(7)).run()
                "8" -> day8.Day8(getInputLinesForDay(8)).run()
                else -> println("Invalid input\n")
            }
        }
    }
}