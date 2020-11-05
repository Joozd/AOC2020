import utils.Timer
import utils.getInputForDay
import utils.getInputLinesForDay

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Please enter the day you want the solutions for. 0 for all!")
            when (readLine()) {
                "0" -> {
                    val t = Timer().apply{
                        start()
                    }
                    day1.Day1(getInputForDay(1)).run()
                    day2.Day2(getInputLinesForDay(2)).run()
                    day3.Day3(getInputForDay(3)).run()
                    day4.Day4(getInputForDay(4)).run()
                    day5.Day5(getInputLinesForDay(5)).run()
                    day6.Day6(getInputLinesForDay(6)).run()
                    day7.Day7(getInputLinesForDay(7)).run()
                    day8.Day8(getInputLinesForDay(8)).run()
                    day9.Day9(getInputLinesForDay(9)).run()
                    println("\n\nDone, total time: ${t.getElapsedTime().toMillis()} milliseconds.")
                }
                "1" -> day1.Day1(getInputForDay(1)).run()
                "2" -> day2.Day2(getInputLinesForDay(2)).run()
                "3" -> day3.Day3(getInputForDay(3)).run()
                "4" -> day4.Day4(getInputForDay(4)).run()
                "5" -> day5.Day5(getInputLinesForDay(5)).run()
                "6" -> day6.Day6(getInputLinesForDay(6)).run()
                "7" -> day7.Day7(getInputLinesForDay(7)).run()
                "8" -> day8.Day8(getInputLinesForDay(8)).run()
                "9" -> day9.Day9(getInputLinesForDay(9)).run()
                "10" -> day10.Day10(getInputForDay(10)).run()
                "11" -> day11.Day11(getInputForDay(11)).run()
                "12" -> day12.Day12(getInputForDay(12)).run()
                else -> println("Invalid input\n")
            }
        }
    }
}