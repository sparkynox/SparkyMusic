package lumi.sparkynox.sparkymusic.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp

@Composable
fun SupportProjectDialog(
    onDismiss: () -> Unit
) {
    val uriHandler = LocalUriHandler.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Support the Project",
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "If you enjoy SparkyMusic, consider giving it a star or following the project!",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                OutlinedButton(
                    onClick = { uriHandler.openUri("https://github.com/sparkynox/SparkyMusic") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Star on GitHub")
                }

                OutlinedButton(
                    onClick = { uriHandler.openUri("https://instagram.com/sparkynox07") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Follow on Instagram")
                }

                OutlinedButton(
                    onClick = { uriHandler.openUri("https://t.me/SparkyNox") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Follow on Telegram")
                }

                OutlinedButton(
                    onClick = { uriHandler.openUri("https://github.com/sparkynox") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Follow on GitHub")
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Don't show again")
            }
        }
    )
}
