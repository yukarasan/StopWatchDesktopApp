import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class StopWatch {
    var time by mutableStateOf("00:00:000")

    private var coroutineScope = CoroutineScope(Dispatchers.Main)
    private var isActive = false

    private var currentTimestamp = 0L
    private var lastTimestamp = 0L

    fun start() {
        if (this@StopWatch.isActive) return

        coroutineScope.launch {
            lastTimestamp = System.currentTimeMillis()
            this@StopWatch.isActive = true
            while (this@StopWatch.isActive) {
                delay(10)
                currentTimestamp += System.currentTimeMillis() - lastTimestamp
                lastTimestamp = System.currentTimeMillis()
                time = formatTime(currentTimestamp)
            }
        }
    }

    private fun formatTime(currentTimestamp: Long): String {
        val localDateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(currentTimestamp),
            ZoneId.systemDefault()
        )

        val formatter = DateTimeFormatter.ofPattern(
            "mm:ss:SSS", Locale.getDefault()
        )

        return localDateTime.format(formatter)
    }

    fun pause() {
        if (isActive) {
            isActive = false
        } else {
            start()
        }
    }

    fun reset() {
        coroutineScope.cancel()
        coroutineScope = CoroutineScope(Dispatchers.Main)
        currentTimestamp = 0L
        lastTimestamp = 0L
        time = "00:00:000"
        isActive = false
    }
}