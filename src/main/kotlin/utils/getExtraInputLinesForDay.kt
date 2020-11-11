package utils

import java.io.File

fun getExtraInputLinesForDay(day: Int, extension: String): List<String> =
        File("inputs\\day$day$extension.txt").readLines()