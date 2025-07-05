package characterDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gameZone.models.CharacterStatus
import com.gameZone.models.StatusColor
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CharacterStatusComponent(characterStatus: CharacterStatus) {
    val colorMapper = when (characterStatus.color) {
        StatusColor.RED -> Color.Red
        StatusColor.YELLOW -> Color.Yellow
        StatusColor.GREEN -> Color.Green
    }
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .background(color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(12.dp))
            .border(width = 2.dp, color = colorMapper, shape = RoundedCornerShape(12.dp))
            .padding(top = 12.dp, bottom = 12.dp, start = 12.dp, end = 48.dp)
    ) {
        Text(text = "Status :", fontSize = 14.sp, color = MaterialTheme.colorScheme.onBackground)
        Text(
            text = characterStatus.displayName,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
private fun PreviewCharacterStatusComponentAlive() {
    val status = CharacterStatus.Alive
    CharacterStatusComponent(status)

}

@Preview
@Composable
private fun PreviewCharacterStatusComponentDead() {
    val status = CharacterStatus.Dead
    CharacterStatusComponent(status)

}

@Preview
@Composable
private fun PreviewCharacterStatusComponentUnknwon() {
    val status = CharacterStatus.Unknown
    CharacterStatusComponent(status)
}

@Preview
@Composable
private fun PreviewCharacterStatusComponentDark() {
    val status = CharacterStatus.Unknown
    CharacterStatusComponent(status)
}