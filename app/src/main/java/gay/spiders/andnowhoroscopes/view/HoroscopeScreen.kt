package gay.spiders.andnowhoroscopes.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import gay.spiders.andnowhoroscopes.R
import gay.spiders.andnowhoroscopes.data.Zodiac
import gay.spiders.andnowhoroscopes.viewmodel.HoroscopeViewModel.Horoscope
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoroscopeScreen(
    horoscope: State<Horoscope>,
    selectedZodiac: Zodiac,
    onNewSelection: (Zodiac) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }

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
                    onClick = { openBottomSheet = true },
                ) {
                    Text(stringResource(R.string.horoscope_cta))
                }
            }
        }
    }
    if (openBottomSheet) {
        ZodiacSelectBottomSheet(
            selectedZodiac = selectedZodiac,
            sheetState = sheetState,
            onNewSelection = onNewSelection,
            onDismissRequest = { openBottomSheet = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZodiacSelectBottomSheet(
    selectedZodiac: Zodiac,
    sheetState: SheetState,
    onNewSelection: (Zodiac) -> Unit,
    onDismissRequest: () -> Unit
) {
    val zodiacs = Zodiac.values()
    val (selectedOption, onOptionSelected) = rememberSaveable { mutableStateOf(selectedZodiac) } // TODO - hoist state?

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(bottom = 30.dp)
        ) {
            items(zodiacs) { zodiac ->

                val color = animateColorAsState(
                    targetValue = if (zodiac == selectedOption) zodiac.getColor()
                        .copy(alpha = 0.2f) else Color.Unspecified,
                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
                    label = ""
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(shape = RoundedCornerShape(15.dp))
                        .background(
                            color = color.value
                        )
                        .selectable(
                            selected = (zodiac == selectedOption),
                            onClick = {
                                onOptionSelected(zodiac)
                                onNewSelection(zodiac)
                            }
                        )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(zodiac.getIcon()),
                            contentDescription = zodiac.getName(),
                            tint = zodiac.getColor(),
                            modifier = Modifier
                                .size(32.dp)
                                .padding(bottom = 4.dp)
                        )
                        Text(zodiac.getName())
                    }
                }
            }
        }
    }
}

private class ZodiacProvider : PreviewParameterProvider<Zodiac> {
    override val values = Zodiac.values().asSequence()
}

@Preview
@Composable
private fun ZodiacPreviews(@PreviewParameter(ZodiacProvider::class) zodiac: Zodiac) {
    val horoscopeState =
        MutableStateFlow(Horoscope.Success(zodiac.getName())).collectAsStateWithLifecycle()
    HoroscopeScreen(
        horoscope = horoscopeState,
        selectedZodiac = zodiac,
        onNewSelection = {}
    )
}