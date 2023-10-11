package gay.spiders.andnowhoroscopes.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import gay.spiders.andnowhoroscopes.viewmodel.HoroscopeViewModel

@AndroidEntryPoint
class HoroscopeActivity: ComponentActivity() {
    private val viewModel by viewModels<HoroscopeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchHoroscope(viewModel.getZodiac())

        setContent {
            var selectedZodiac by remember { mutableStateOf(viewModel.getZodiac()) }
            HoroscopeScreen(
                horoscope = viewModel.horoscope.collectAsStateWithLifecycle(),
                selectedZodiac = selectedZodiac,
                onNewSelection = { zodiac ->
                    selectedZodiac = zodiac
                    viewModel.setZodiac(zodiac)
                    viewModel.fetchHoroscope(zodiac)
                }
            )
        }
    }

}