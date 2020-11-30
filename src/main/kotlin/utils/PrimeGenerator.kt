package utils

import utils.extensions.sqrt

class PrimeGenerator(private val largest: Int) {
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

    /**
     * NOTE: This only gets the prime factors in this PrimeGenerator. Make use [largest] is large enough!
     */
    fun primeFactors(number: Int) = primes.filter { number % it == 0 }

    companion object {
        fun forFactoring(numberToFactor: Int) = PrimeGenerator(numberToFactor.sqrt())
    }
}