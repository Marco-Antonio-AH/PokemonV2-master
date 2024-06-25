package mx.marco.presentation.composables.images

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun ImageNormal(modifier: Modifier, @DrawableRes imageName: Int, contentScale: ContentScale = ContentScale.Fit) {

    androidx.compose.foundation.Image(
        painter = painterResource(id = imageName),
        contentDescription = "",
        contentScale = contentScale,
        modifier = modifier
    )


}