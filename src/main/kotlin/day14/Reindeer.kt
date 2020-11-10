package day14

class Reindeer(val name: String, val speed: Int, val endurance: Int, val rest: Int){
    private var pos = 0
    private var sc = 0
    private var remainingRun = endurance
    private var remainingRest = rest
    private val running: Boolean
        get()= remainingRun >0

    //run 1 tick
    fun run(){
        if(running){
            remainingRun--
            pos += speed
        }
        else{
            if (remainingRest == 1){
                remainingRun = endurance
                remainingRest = rest
            } else remainingRest--
        }
    }

    fun point(){
        sc++
    }

    val distance: Int
        get()=pos
    val score: Int
        get() = sc

    override fun toString()= "Reindeer($name, $speed km/s($endurance s), rest $rest.)"
    companion object{
        fun parse(s: String) = s.split(' ').let{
            Reindeer(it[0], it[3].toInt(), it[6].toInt(), it[13].toInt())
        }
    }
}