package utils.gameOfLife

abstract class GameOfLifeNode<T>(val x: Int, val y : Int) {
    abstract var state: T
    protected var nextState: T = state

    protected var neighbours: List<GameOfLifeNode<T>> = emptyList()

    val changing: Boolean
        get() = nextState != state

    open fun fillNeighbours(map: List<List<GameOfLifeNode<T>>>) {
        neighbours = listOfNotNull(map.getOrNull(y-1)?.getOrNull(x-1),
            map.getOrNull(y-1)?.getOrNull(x),
            map.getOrNull(y-1)?.getOrNull(x+1),
            map.getOrNull(y)?.getOrNull(x-1),
            map.getOrNull(y)?.getOrNull(x+1),
            map.getOrNull(y+1)?.getOrNull(x-1),
            map.getOrNull(y+1)?.getOrNull(x),
            map.getOrNull(y+1)?.getOrNull(x+1))
    }

    fun countNeighbours(condition: (T) -> Boolean): Int = neighbours.count {condition(it.state)}

    /**
     * This is what happens every tick
     * @return the value [state] should have on the next tick or null if no change
     */
    abstract fun assignNextState(): T?

    fun tick(){
        assignNextState()?.let { nextState = it }
    }


    fun execute(){
        state = nextState
    }
}