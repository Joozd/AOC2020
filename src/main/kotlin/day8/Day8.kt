package day8

import Solution

// not 1098

class Day8(private val input: List<String>): Solution() {
    override fun first() {
        println("string literal character thingies: ${input.sumBy { countExtras(it) }}")
    }

    override fun second() {
        println("answer 2: ${input.sumBy { extraEscaping(it) }}")
    }


    fun countExtras(str: String): Int{
        var s = str.drop(1).dropLast(1)
        var counter = 2
        //first, remove escaped slashes and count them
        while(SLASHES in s){
            s = s.take(s.indexOf(SLASHES)) + s.drop(s.indexOf(SLASHES)+2)
            counter++
        }
        counter += """\\x[0-9a-fA-F]""".toRegex().findAll(s).toList().size * 3
        counter += """\\"""".toRegex().findAll(s).toList().size

        return counter
    }

    fun extraEscaping(str: String): Int =
        2 + """["\\]""".toRegex().findAll(str).toList().size




    companion object{
        private const val SLASHES = "\\\\"
    }
}