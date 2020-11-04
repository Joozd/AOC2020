package utils

import java.time.Duration
import java.time.Instant


class Timer {
    private var startTime: Instant? = null
    fun start() {
        startTime = Instant.now()
    }
    fun getElapsedTime(): Duration = startTime?.let {
        Duration.between(it, Instant.now())
    }?: Duration.ZERO
}