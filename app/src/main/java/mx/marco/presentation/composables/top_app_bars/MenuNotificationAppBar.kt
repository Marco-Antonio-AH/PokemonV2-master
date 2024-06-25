@file:OptIn(ExperimentalMaterial3Api::class)

package mx.marco.presentation.composables.top_app_bars

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuNotificationAppBar(
    title: String,
    colorIcons: Color = MaterialTheme.colorScheme.primary,
    transparent: Boolean = false
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = if (transparent){
                Color.White.copy(alpha = 0f)
            }else{
                MaterialTheme.colorScheme.secondary
            },
                titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart){
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight(700),
                    color = colorIcons,
                    textAlign = TextAlign.Start
                )
            }
        },
        navigationIcon = {
        },
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        scrollBehavior = scrollBehavior,
        actions = {

        }
    )
}

@Preview(showBackground = true)
@Composable
fun MenuNotificationAppBarPreview() {
    MenuNotificationAppBar("Titulo")
}