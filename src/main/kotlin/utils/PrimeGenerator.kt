package utils

class PrimeGenerator(largest: Int) {
    private var sieve = (2..largest).toList()
    private val primesWorker = mutableListOf<Int>().apply{
        while (sieve.isNotEmpty()){
            add(sieve.first().also{ n ->
                sieve = sieve.filter{ it % n != 0}
            })
        }
    }
    val primes: List<Int>
        get() = primesWorker
}