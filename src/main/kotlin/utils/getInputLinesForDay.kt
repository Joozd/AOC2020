package utils

import java.io.File

fun getInputLinesForDay(day: Int): List<String> =
    File("inputs\\day$day.txt").readLines()