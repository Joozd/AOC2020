package utils

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.InputStreamReader

fun getInputReaderForDay(day: Int): BufferedReader =
    File("inputs\\day$day.txt").inputStream().bufferedReader()
