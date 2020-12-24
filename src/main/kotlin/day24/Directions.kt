package day24

class Directions(private val input: String): Iterable<String> {

    override fun iterator(): Iterator<String> = object: Iterator<String>{
        val s = input.toMutableList()
        override fun hasNext() = s.isNotEmpty()

        override fun next(): String {
            val c = s.removeAt(0)
            return when(c){
                'e' -> "e"
                'w' -> "w"
                'n' -> listOf(c, s.removeAt(0)).joinToString("")
                's' -> listOf(c, s.removeAt(0)).joinToString("")
                else -> error ("ERROR 2: BAD INPUT IN Directions")
            }
        }
    }

}