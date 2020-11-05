package day10

/**
 * @param number: sequence number (1 last)
 * @param name: Element name (eg. "TM")
 * @param string: The string this element represents (eg. "11131221133112")
 * @param evolution: List of elements this string evulves into (eg "Er", "Ca", "Co")
 */
data class Element(val number: Int, val name: String, val string: String, val evolution: List<String>){
    val size = string.length

    companion object{
        /**
         * Expects a tab-separated string with data
         */
        fun of(s: String): Element{
            val words = s.split("\t")
            val number = words[0].toInt()
            val name = words[1]
            val string = words[3]
            val evolution = words[2].split(' ').filter{ it.isNotEmpty() }
            return Element(number, name, string, evolution)
        }
    }
}
