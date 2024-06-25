package mx.marco.presentation.composables.search

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import mx.com.satoritech.journeys.util.clearFocusOnKeyboardDismiss
import mx.marco.presentation.theme.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldSearch(
    value: String,
    onValueTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String
) {
    val interactionSource = remember { MutableInteractionSource() }
    val spacing = LocalSpacing.current
    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueTextChange,
            modifier = modifier
                .border(
                    width = (.5).dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(shape = RoundedCornerShape(20.dp))
                .fillMaxWidth(),
            interactionSource = interactionSource,
            enabled = true,
            singleLine = true,
            textStyle = MaterialTheme.typography.labelMedium.copy(color =Color.Black, fontWeight = FontWeight(400))
        ) { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = "",
                visualTransformation = VisualTransformation.None,
                innerTextField = innerTextField,
                singleLine = true,
                enabled = true,
                interactionSource = interactionSource,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.Gray
                    )
                },
                placeholder = {
                    if (value.isEmpty()){
                        Text(
                            modifier = Modifier.padding(start = spacing.spaceMedium),
                            text = hint,
                            style = MaterialTheme.typography.labelLarge,
                            color = if (isSystemInDarkTheme()) Color(0xFF969EBD) else Color.Gray
                        )
                    }/*else{
                        Text(
                            modifier = Modifier,
                            text = value,
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }*/
                },
                contentPadding = PaddingValues(0.dp), // this is how you can remove the padding
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.primary,
                    errorTextColor = Color.Red,
                    errorContainerColor = Color.White,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    disabledContainerColor = MaterialTheme.colorScheme.background,
                    focusedLabelColor = MaterialTheme.colorScheme.background,
                    unfocusedLabelColor = MaterialTheme.colorScheme.background,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.background,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = MaterialTheme.colorScheme.background,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
                )
            )
        }
    }
}