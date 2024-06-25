package mx.marco.presentation.composables.buttons

import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import mx.marco_ah.R


@Composable
fun BasicButton(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.onPrimary,
    contentColor: Color = MaterialTheme.colorScheme.surface,
    @StringRes label: Int,
    enabled: Boolean = true,
    bold: Boolean = false,
    alternative: Boolean = false,
    content: @Composable () -> Unit = {},
    style: TextStyle = MaterialTheme.typography.labelLarge,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    fontSize: TextUnit = TextUnit.Unspecified,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick.invoke() },
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor =
            if (alternative) {
                containerColor
            } else {
                contentColor
            },
            contentColor = if (alternative) {
                contentColor
            } else {
                containerColor
            }
        ),
        shape = shape,
        //border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary)
    ) {
        content()
        Text(
            modifier = Modifier,
            text = stringResource(id = label).uppercase(),
            color = if (alternative) {
                contentColor
            } else {
                containerColor
            },
            style = style,
            fontFamily = FontFamily(
                if (bold) {
                    Font(R.font.quicksand_bold)
                } else {
                    Font(R.font.quicksand_regular)
                }
            ),
            textAlign = TextAlign.Center,
            fontSize = fontSize
        )
        //}
    }
    FontFamily
}