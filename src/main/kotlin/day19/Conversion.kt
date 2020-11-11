package day19

class Conversion(private val input: String, private val output: String) {
    private val l = input.length
    private val l2 = output.length
    fun possibleReplacements(molecule: String): List<String>{
        val results = mutableListOf<String>()
        (0..molecule.length-l).forEach{
            if (molecule.drop(it).startsWith(input))
                results.add((molecule.take(it) + output + molecule.drop(it + l)))
        }
        return results
    }

    fun possibleReductions(molecule: String): List<String>{
        val results = mutableListOf<String>()
        (0..molecule.length-l).forEach{
            if (molecule.drop(it).startsWith(output))
                results.add((molecule.take(it) + input + molecule.drop(it + l2)))
        }
        return results
    }

    fun reduceOnce(m: String): String? = m.indexOf(output).let{
        if (it == -1) null
        else {
            m.take(it) + input + m.drop(it+l2)
        }
    }


    companion object{
        fun of(s: String): Conversion = s.split(' ').let{
            Conversion(it.first(), it.last())
        }
    }
}