import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    val stopWatch = remember { StopWatch() }

    var stopWatchStarted by remember { mutableStateOf(false) }
    var resumeOrPause by remember { mutableStateOf("Stop") }
    var clicked by remember { mutableStateOf(false) }

    var startButtonEnabled by remember { mutableStateOf(true) }
    var pauseAndResetButtonEnabled by remember { mutableStateOf(false) }

    var resumeOrPauseButtonColor by remember { mutableStateOf(false) }

    MaterialTheme {
        StopWatchDisplay(
            time = stopWatch.time,
            onStartClick = {
                if (!stopWatchStarted) {
                    stopWatch.start()
                    stopWatchStarted = true
                    startButtonEnabled = false
                    pauseAndResetButtonEnabled = true
                }
            },
            onPauseClick = {
                if (stopWatchStarted) {
                    stopWatch.pause()
                    clicked = !clicked

                    resumeOrPause = if (clicked) {
                        "Resume"
                    } else {
                        "Stop"
                    }

                    resumeOrPauseButtonColor = !resumeOrPauseButtonColor
                }
            },
            onResetClick = {
                stopWatch.reset()
                stopWatchStarted = false
                resumeOrPause = "Stop"
                clicked = false
                startButtonEnabled = true
                pauseAndResetButtonEnabled = false
                resumeOrPauseButtonColor = false
            },
            resumeOrPause = resumeOrPause,
            startButtonEnabled = startButtonEnabled,
            pauseAndResetButtonEnabled = pauseAndResetButtonEnabled,
            resumeOrPauseButtonColor = resumeOrPauseButtonColor,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        )
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
