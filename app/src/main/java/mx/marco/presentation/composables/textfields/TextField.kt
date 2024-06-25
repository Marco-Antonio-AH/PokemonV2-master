package mx.marco.presentation.composables.textfields

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import mx.marco.presentation.theme.LocalSpacing
import mx.marco.presentation.theme.quickSand
import mx.marco.presentation.theme.quickSandBold
import mx.marco_ah.R


@Composable
fun GenericTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueTextChange: (String) -> Unit,
    isError: Boolean = false,
    isRecoverAccount: Boolean = false,
    @StringRes label: Int? = null,
    @StringRes hint : Int = R.string.nothing,
    singleLine: Boolean = true,
    borderColor: Color = MaterialTheme.colorScheme.primary,
    isTextArea: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Done
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    @StringRes supportingText: Int? = null,
    @StringRes errorMessage: Int? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    ) {
    val spacing = LocalSpacing.current
    Column(
        modifier = modifier
    ) {
        if (label != null) {
            Text(
                text = stringResource(id = label),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                fontFamily = quickSandBold,
                )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
        }
        OutlinedTextField(
            value = value,
            onValueChange = onValueTextChange,
            modifier = modifier

                .height(if (isTextArea) 120.dp else 52.dp)
                .bottomBorder(1.dp, MaterialTheme.colorScheme.primary)
            ,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary,
                errorTextColor = Color.Red,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(0.dp),
            trailingIcon = trailingIcon,
            /*supportingText = {
                supportingText?.let {
                    Text(
                        text = stringResource(id = supportingText),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },*/
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            isError = isError,
            singleLine = singleLine,
            textStyle = if (isRecoverAccount)
                TextStyle.Default.copy(fontSize = 10.sp, fontFamily = quickSand)
            else
                TextStyle.Default.copy(fontFamily = quickSand),
            visualTransformation = visualTransformation,
            placeholder = {
                Text(
                    text = stringResource(id = hint),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge,
                    lineHeight = 300.sp,
                    letterSpacing = 0.sp
                )
            },
        )
        if(isError && errorMessage!=null){
            Text(
                text = stringResource(id = errorMessage),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height - strokeWidthPx/2

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = width , y = height),
                strokeWidth = strokeWidthPx
            )
        }
    }
)