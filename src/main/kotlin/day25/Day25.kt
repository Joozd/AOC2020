package day25

import Solution
import utils.extensions.isDigits

/**
 * I thought this one would be kind of annoying math stuff.
 * It turns out running throuhg my LCG for 18 million + times only takes a good 0.2 seconds ^-^
 */

class Day25(input: String): Solution(){
    private val column: Int
    private val row: Int
    init{
        input.filter{ it !in ".,"}.split(' ').filter{it.isDigits()}.filter{it.isNotBlank()}.map{it.toInt()}.let{
            column = it[1]
            row = it[0]
        }
    }

    private fun infinitePaper(c: Int, r: Int) = ((c+r-2)*(c+r-1))/2 + c

    private fun lcg(y: Int, a: Int, m: Int): Int{
        val long: Long = y.toLong() * a
        return (long%m).toInt()
    }

    override fun first() {
        println("c: $column, r: $row")
        val index = infinitePaper(column, row)
        println("index = $index")

        var y = 20151125
        val a = 252533
        val m = 33554393

        repeat(index-1) {
            y = lcg(y,a,m)
        }
        println(y)
    }

}