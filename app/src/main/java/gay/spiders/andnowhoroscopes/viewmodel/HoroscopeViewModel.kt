package gay.spiders.andnowhoroscopes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gay.spiders.andnowhoroscopes.data.Zodiac
import gay.spiders.andnowhoroscopes.repository.HoroscopeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor(
    private val horoscopeRepository: HoroscopeRepository
): ViewModel() {

    // TODO - figure out how to make this less messy
    private val _horoscope = MutableStateFlow<Horoscope>(Horoscope.Default)
    val horoscope = _horoscope.asStateFlow()

    fun fetchHoroscope(sign: Zodiac) {
        horoscopeRepository.getHoroscope(sign)
            .onEach {
                _horoscope.value = Horoscope.Success(it)
            }
            .launchIn(viewModelScope)
    }

    sealed class Horoscope {
        data object Default : Horoscope()
        data class Success(val horoscope: String) : Horoscope()
    }
}