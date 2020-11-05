package day12

class Obj(private val type: Int) {
    private var children: MutableList<Obj> = emptyList<Obj>().toMutableList()
    private var text: String = "" // text without child objects
    private val red: Boolean
        get() = type == OBJECT && "red" in text

    /**
     * sum is the sum of this (if not a red object) and all children
     */
    val sum: Int
        get() = if (!red || type == ARRAY)
            "-?[0-9]+".toRegex().findAll(text).sumBy{it.value.toInt()} + children.sumBy { it.sum }
        else 0

    /**
     * Adds data from [json] to this object
     * When it is at the end of the first object it finds, it returns the remaining json data for recursive filling of children.
     * usage: Just drop all your JSON goodness in the first one you construct.
     * example:
     * Obj().apply { addJson(jsonData) }
     */
    fun addJson(json: String): String{
        var result: String
        val j = json.toMutableList()
        val textBuilder = ArrayList<Char>()
        repeat(json.length){
            when(j[0]){
                '[' -> {
                    text += textBuilder.toCharArray().concatToString()
                    children.add(Obj(ARRAY).apply {
                        addJson(j.drop(1).toCharArray().concatToString()).let{
                            result = this@Obj.addJson(it)
                        }
                    })
                    return result
                }
                '{' -> {
                    text += textBuilder.toCharArray().concatToString()
                    children.add(Obj(OBJECT).apply {
                        addJson(j.drop(1).toCharArray().concatToString()).let{
                            result = this@Obj.addJson(it)
                        }
                    })
                    return result
                }
                ']' -> {
                    if (this.type == ARRAY) { // this object is done, return to previous
                        text += textBuilder.toCharArray().concatToString()
                        return j.drop(1).toCharArray().concatToString()
                    }
                    else {
                        textBuilder.add(']')
                        j.removeAt(0)
                        println("Warning: found a closing \']\' inside an OBJECT")
                    }
                }
                '}' -> {
                    if (this.type == OBJECT) { // this object is done, return to previous
                        text += textBuilder.toCharArray().concatToString()
                        return j.drop(1).toCharArray().concatToString()
                    }
                    else {
                        textBuilder.add(']')
                        j.removeAt(0)
                        println("Warning: found a closing \'}\' inside an ARRAY")
                    }
                }
                else -> {
                    textBuilder.add(j[0])
                    j.removeAt(0)
                }
            }
        }
        //if we get here, last object was not closed corretly or at end of string
        println("suspected EOF")
        if (json.isEmpty()) return ""
        error("current object not closed correctly: ${json.take(100)}...${json.drop(100)}")
    }

    companion object{
        const val OBJECT = 1
        const val ARRAY = 2
    }
}