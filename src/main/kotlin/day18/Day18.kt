package day18

import Solution

class Day18(val input: List<String>): Solution() {
    private val grid: List<Lamp> by lazy{
        input.mapIndexed { y, s ->
            s.mapIndexed { x, c ->
                Lamp(x,y,c=='#')
            }
        }.flatten().also{
            it.chunked(100).let{g ->
                it.forEach{l ->
                    with (l){
                        addNeighbour(g.getOrNull(y-1)?.getOrNull(x-1))
                        addNeighbour(g.getOrNull(y-1)?.getOrNull(x))
                        addNeighbour(g.getOrNull(y-1)?.getOrNull(x+1))
                        addNeighbour(g.getOrNull(y)?.getOrNull(x-1))
                        addNeighbour(g.getOrNull(y)?.getOrNull(x+1))
                        addNeighbour(g.getOrNull(y+1)?.getOrNull(x-1))
                        addNeighbour(g.getOrNull(y+1)?.getOrNull(x))
                        addNeighbour(g.getOrNull(y+1)?.getOrNull(x+1))
                    }
                }
            }
        }
    }

    private val grid2: List<Lamp> by lazy{
        input.mapIndexed { y, s ->
            s.mapIndexed { x, c ->
                if (x in listOf(0,99) && y in listOf(0,99))
                    Lamp(x,y,on = true, alwaysOn = true)
                else
                Lamp(x,y,c=='#')
            }
        }.flatten().also{
            it.chunked(100).let{g ->
                it.forEach{l ->
                    with (l){
                        addNeighbour(g.getOrNull(y-1)?.getOrNull(x-1))
                        addNeighbour(g.getOrNull(y-1)?.getOrNull(x))
                        addNeighbour(g.getOrNull(y-1)?.getOrNull(x+1))
                        addNeighbour(g.getOrNull(y)?.getOrNull(x-1))
                        addNeighbour(g.getOrNull(y)?.getOrNull(x+1))
                        addNeighbour(g.getOrNull(y+1)?.getOrNull(x-1))
                        addNeighbour(g.getOrNull(y+1)?.getOrNull(x))
                        addNeighbour(g.getOrNull(y+1)?.getOrNull(x+1))
                    }
                }
            }
        }
    }


    override fun first() {
        repeat(100){
            grid.forEach{l ->
                l.cycle()
            }
            grid.forEach {l ->
                l.execute()
            }
        }
        println("${grid.filter { it.on }.size} lights are on.")
    }

    override fun second() {
        repeat(100){
            grid2.forEach{ l->
                l.cycle()
            }
            grid2.forEach {l ->
                l.execute()
            }
        }
        println("${grid2.filter { it.on }.size} lights are on.")
    }
}