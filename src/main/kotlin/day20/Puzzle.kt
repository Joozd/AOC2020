package day20

class Puzzle(private val allPieces: MutableList<Tile>) {
    /**
     * null is an empty spot, a tile in a place is a correctly placed tile
     */
    private val grid = Array(12){ Array<Tile?>(12) { null } }
    private var currentLine = 0 // 0..11
    private var currentColumn = 0 // 0..11

    private val cornerPieces = allPieces.filter{
        it.edgeIDsUnsorted.count { e -> allPieces.filter { tt -> tt.number != it.number }.any { t -> e in t.edgeIDsUnsorted } } == 4
    }.toMutableList()
    private val sidePieces = allPieces.filter {
        it.edgeIDsUnsorted.count { e -> allPieces.filter { tt -> tt.number != it.number }.any { t -> e in t.edgeIDsUnsorted } } == 6
    }.toMutableList()
    private val middlePieces = allPieces.filter {it !in cornerPieces + sidePieces}.toMutableList()

    private fun placeFirstCorner() {
        grid[currentLine][currentColumn++] = cornerPieces.removeFirst().apply {
            allPieces.remove(this)
            allPieces.map { it.edgeIDsUnsorted }.flatten().let {
                while (bottom !in it || right !in it) {
                    nextOrientation()
                }
            }
        }
    }

    private fun addPiece() {
        // fill first line looking at piece to the left
        if (currentLine == 0) grid[0][currentColumn] =
            allPieces.first { grid[currentLine][currentColumn - 1]!!.right in it.edgeIDsUnsorted }.apply {
                allPieces.remove(this)
                while (left != grid[0][currentColumn-1]!!.right) {
                    nextOrientation()
                }
            }

        //fill other lines looking at piece above
        else grid[currentLine][currentColumn] =
            allPieces.first { it.edgeIDsUnsorted.any { it == grid[currentLine - 1][currentColumn]!!.bottom } }.apply {
                allPieces.remove(this)
                while (top != grid[currentLine - 1][currentColumn]!!.bottom) nextOrientation()
            }
        if (++currentColumn > LAST_COLUMN){
            currentColumn = 0
            currentLine++
        }
    }

    init{
        placeFirstCorner()
        while (currentLine <= LAST_LINE){
            addPiece()
        }
    }

    fun result(): List<String> =
        grid.map{ lineOfTiles ->
            val contents = lineOfTiles.map{it!!.showContent()}
            contents.first().mapIndexed { index, _ ->
                contents.map{ c-> c[index] }.joinToString("")
            }
        }.flatten()

    companion object{
        const val LAST_LINE = 11
        const val LAST_COLUMN = 11
    }
}