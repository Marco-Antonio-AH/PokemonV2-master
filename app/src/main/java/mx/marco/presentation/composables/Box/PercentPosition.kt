package mx.marco.presentation.composables.Box

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PercentPosition( modifier : Modifier = Modifier,x : Float, y : Float, content : @Composable () -> Unit){
    Box(modifier = modifier
        .fillMaxHeight(x)
        .fillMaxWidth(y),
        Alignment.BottomEnd
    ) {
        content()
    }
}

