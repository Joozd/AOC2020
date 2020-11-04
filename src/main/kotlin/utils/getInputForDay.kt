package utils

import java.io.File

fun getInputForDay(day: Int): String =
    File("inputs\\day$day.txt").readText()
