package day20

import Solution
import utils.extensions.sqrt

class Day20(input: String): Solution (){
    private val minimumPresents = input.toInt()/10 // We con just ignore the whole "times ten" part
    var answer1: Int? = null


    private fun getFactors(number: Int): List<Int>{
        val list = mutableListOf(1)
        for (i in (1..number.sqrt())){
            if (number%i == 0)
                (number/i).let{
                    list.add(i)
                    if (it != i) list.add(it)
                }
        }
        return list
    }

    private fun getFactorsWithMax(number: Int, max: Int = 50): List<Int>{
        return getFactors(number).filter{number/it <= max}
    }

    override fun first() {
        var i = 10000
        while(true){
            getFactors(i).sum().let{
                if (it >= minimumPresents){
                    println("found it! $i with $it presents!")
                    answer1 = i
                    return
                }
            }
            i+=2
        }
    }

    override fun second() {
        var i = answer1!!
        val newMinimum = minimumPresents*10
        while(true){
            getFactorsWithMax(i).sum().let{
                if (it*11 >= newMinimum){
                    println("found it! $i with ${it*11} presents!")
                    answer1 = it
                    return
                }
            }
            i+=2
        }
    }
}