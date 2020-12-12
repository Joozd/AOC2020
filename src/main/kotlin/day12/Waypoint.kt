package day12

class Waypoint(var x: Int =10, var y: Int = -1) {
    fun execute(s: String){
        val amount =  s.drop(1).toInt()
        when(s.first()){
            'N' -> y -= amount
            'E' -> x += amount
            'S' -> y += amount
            'W' -> x -= amount
            'L' -> when (amount / 90 % 4) {
                    1 -> {
                        val temp = x
                        x = y
                        y = temp * -1
                    }
                    2 -> {
                        x *= -1
                        y *= -1
                    }
                    3 -> {
                        val temp = x
                        x = y * -1
                        y = temp
                    }
                }
            'R' -> when (amount / 90 % 4) {
                3 -> {
                    val temp = x
                    x = y
                    y = temp * -1
                }
                2 -> {
                    x *= -1
                    y *= -1
                }
                1 -> {
                    val temp = x
                    x = y * -1
                    y = temp
                }
            }
            else -> error ("Don't send F instructions to Waypoint")
        }
    }
}