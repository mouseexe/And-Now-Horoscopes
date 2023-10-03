package gay.spiders.andnowhoroscopes.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import dagger.hilt.android.AndroidEntryPoint
import gay.spiders.andnowhoroscopes.viewmodel.HoroscopeViewModel
import gay.spiders.andnowhoroscopes.data.Zodiac
import kotlin.random.Random

@AndroidEntryPoint
class HoroscopeActivity: ComponentActivity() {
    private val viewModel by viewModels<HoroscopeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchRandomZodiac()

        setContent {
            HoroscopeScreen(
                horoscope = viewModel.horoscope.collectAsState(),
                onCTAClick = { fetchRandomZodiac() }
            )
        }
    }

    private fun fetchRandomZodiac() {
        val zodiacs = Zodiac.values()
        viewModel.fetchHoroscope(zodiacs[Random.nextInt(zodiacs.size)])
    }
}