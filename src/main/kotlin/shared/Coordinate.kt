package shared

data class Coordinate(val x: Int, val y: Int){
    override fun toString() = "($x,$y)"

    companion object{
        fun parse(s: String): Coordinate{
            require (',' in s)
            s.split(',').map{it.toInt()}.let{
                return Coordinate(it[0], it[1])
            }
        }
    }
}