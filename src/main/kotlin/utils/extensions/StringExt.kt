package utils.extensions

fun String.isDigits() = this.all{it.isDigit()}
fun String.getDigits() = this.filter { it.isDigit() }

fun String.splitToWords() = this.split(' ')

fun String.getOnlyNumbers(): List<Int> = this.splitToWords().filter{it.isDigits()}.map{it.toInt()}