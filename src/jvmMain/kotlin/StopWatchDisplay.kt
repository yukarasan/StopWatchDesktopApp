import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StopWatchDisplay(
    time: String,
    onStartClick: () -> Unit,
    onPauseClick: () -> Unit,
    onResetClick: () -> Unit,
    resumeOrPause: String,
    startButtonEnabled: Boolean,
    pauseAndResetButtonEnabled: Boolean,
    resumeOrPauseButtonColor: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = time,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.White
        )
        Spacer(Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onStartClick,
                enabled = startButtonEnabled,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = Color(87, 150, 92)
                ),
                elevation = ButtonDefaults.elevation(
                    focusedElevation = 10.dp,
                    pressedElevation = 20.dp
                ),
                modifier = Modifier.padding(5.dp)
            ) {
                Text("Start")
            }
            Button(
                onClick = onPauseClick,
                enabled = pauseAndResetButtonEnabled,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    backgroundColor = (
                            if (resumeOrPauseButtonColor) {
                                Color(87, 150, 92)
                            } else {
                                Color(201, 79, 79)
                            }
                    )
                ),
                elevation = ButtonDefaults.elevation(
                    focusedElevation = 10.dp,
                    pressedElevation = 20.dp
                ),
                modifier = Modifier.padding(5.dp)
            ) {
                Text(resumeOrPause)
            }
            Button(
                onClick = onResetClick,
                enabled = pauseAndResetButtonEnabled,
                shape = RoundedCornerShape(20.dp),
                elevation = ButtonDefaults.elevation(
                    focusedElevation = 10.dp,
                    pressedElevation = 20.dp
                ),
                modifier = Modifier.padding(5.dp)
            ) {
                Text("Reset")
            }
        }
    }
}