import utils.Timer
import utils.getExtraInputLinesForDay
import utils.getInputForDay
import utils.getInputLinesForDay

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Please enter the day you want the solutions for. 0 for all!")
            when (readLine()) {
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
                "13" -> day13.Day13(getInputLinesForDay(13)).run()
                "14" -> day14.Day14(getInputLinesForDay(14)).run()
                "15" -> day15.Day15(getInputLinesForDay(15)).run()
                "16" -> day16.Day16(getInputLinesForDay(16)).run()
                "17" -> day17.Day17(getInputLinesForDay(17)).run()
                "18" -> day18.Day18(getInputLinesForDay(18)).run()
                "19" -> day19.Day19(getInputLinesForDay(19)).run()
                "20" -> day20.Day20(getInputForDay(20)).run()
                "21" -> day21.Day21(getInputLinesForDay(21), getExtraInputLinesForDay(21, "a")).run()
                "22" -> day22.Day22(getInputLinesForDay(22)).run()
                "23" -> day23.Day23(getInputLinesForDay(23)).run()
                "24" -> day24.Day24(getInputLinesForDay(24)).run()

                "0" -> {
                    val t = Timer().apply{ start() }
                    day1.Day1(getInputForDay(1)).run()
                    day2.Day2(getInputLinesForDay(2)).run()
                    day3.Day3(getInputForDay(3)).run()
                    day4.Day4(getInputForDay(4)).run()
                    day5.Day5(getInputLinesForDay(5)).run()
                    day6.Day6(getInputLinesForDay(6)).run()
                    day7.Day7(getInputLinesForDay(7)).run()
                    day8.Day8(getInputLinesForDay(8)).run()
                    day9.Day9(getInputLinesForDay(9)).run()
                    day10.Day10(getInputForDay(10)).run()
                    day11.Day11(getInputForDay(11)).run()
                    day12.Day12(getInputForDay(12)).run()
                    day13.Day13(getInputLinesForDay(13)).run()
                    day14.Day14(getInputLinesForDay(14)).run()
                    day15.Day15(getInputLinesForDay(15)).run()
                    day16.Day16(getInputLinesForDay(16)).run()
                    day17.Day17(getInputLinesForDay(17)).run()
                    day18.Day18(getInputLinesForDay(18)).run()
                    day19.Day19(getInputLinesForDay(19)).run()
                    day20.Day20(getInputForDay(20)).run()
                    day21.Day21(getInputLinesForDay(21), getExtraInputLinesForDay(21, "a")).run()
                    day22.Day22(getInputLinesForDay(22)).run()
                    day23.Day23(getInputLinesForDay(23)).run()
                    day24.Day24(getInputLinesForDay(24)).run()

                    println("\n\nDone, total time: ${t.getElapsedTime().toMillis()} milliseconds.")
                }


                else -> println("Invalid input\n")
            }
        }
    }
}