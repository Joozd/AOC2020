package day12

/*
NORTH = 0
WEST = 1
SOUTH = 2
EAST = 3
*/
class Boat(var x: Int = 0, var y: Int = 0) {
    var direction = 3
    fun move(s: String){
        val amount = s.drop(1).toInt()
        when(s.first()){
            'F' -> {
                x += amount *((direction -2) % 2)
                y += amount * ((direction-1) % 2)
            }
            'L' -> direction = (direction + amount/90+4) % 4
            'R' -> direction = (direction - amount/90+4) % 4
            else -> error("only give me LRF instructions")
        }
    }
}