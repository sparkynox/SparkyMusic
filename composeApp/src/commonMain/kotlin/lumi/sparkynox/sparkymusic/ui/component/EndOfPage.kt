package lumi.sparkynox.sparkymusic.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import lumi.sparkynox.sparkymusic.domain.extension.now
import lumi.sparkynox.sparkymusic.ui.theme.typo
import lumi.sparkynox.sparkymusic.utils.VersionManager
import org.jetbrains.compose.resources.stringResource
import sparkymusic.composeapp.generated.resources.Res
import sparkymusic.composeapp.generated.resources.app_name
import sparkymusic.composeapp.generated.resources.version_format

@Composable
fun EndOfPage(withoutCredit: Boolean = false) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(120.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        // Text and huge spacing removed per user request
    }
}