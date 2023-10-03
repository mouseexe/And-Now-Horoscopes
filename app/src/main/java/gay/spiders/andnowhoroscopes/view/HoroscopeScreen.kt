package gay.spiders.andnowhoroscopes.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import gay.spiders.andnowhoroscopes.R
import gay.spiders.andnowhoroscopes.data.Zodiac
import gay.spiders.andnowhoroscopes.viewmodel.HoroscopeViewModel.Horoscope
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun HoroscopeScreen(
    horoscope: State<Horoscope>,
    onCTAClick: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = when (horoscope.value) {
                        is Horoscope.Success -> (horoscope.value as Horoscope.Success).horoscope
                        else -> ""
                    },
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(30.dp)
                )
                Button(
                    onClick = onCTAClick,
                ) {
                    Text(stringResource(R.string.next_zodiac_cta))
                }
            }
        }
    }
}

private class ZodiacProvider: PreviewParameterProvider<Zodiac> {
    override val values = Zodiac.values().asSequence()
}

//@Preview
@Composable
private fun ZodiacPreviews(@PreviewParameter(ZodiacProvider::class) zodiac: Zodiac) {
    val horoscopeState = MutableStateFlow(Horoscope.Success("$zodiac is gonna have a great day!")).collectAsState()
    HoroscopeScreen(
        horoscope = horoscopeState,
        onCTAClick = {}
    )
}

@Preview
@Composable
private fun ScorpioZodiacPreview() {
    val zodiac = Zodiac.SCORPIO
    val horoscopeState = MutableStateFlow(Horoscope.Success("$zodiac is gonna have a great day!")).collectAsState()
    HoroscopeScreen(
        horoscope = horoscopeState,
        onCTAClick = {}
    )
}