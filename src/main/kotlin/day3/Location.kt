package day3

data class Location(var x: Int, var y: Int) {
    fun move(direction: Char){
        when (direction){
            UP -> y++
            DOWN -> y--
            LEFT -> x--
            RIGHT -> x++
            else -> println("Location: Wrong input: $direction\n")
        }
    }

    val coordinate: Coordinate
        get() = Coordinate(x,y)


    companion object{
        const val UP = '^'
        const val DOWN = 'v'
        const val LEFT = '<'
        const val RIGHT = '>'
    }
}