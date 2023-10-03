package gay.spiders.andnowhoroscopes.repository

import gay.spiders.andnowhoroscopes.data.Zodiac
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class HoroscopeRepositoryImpl @Inject constructor(): HoroscopeRepository {
    override fun getHoroscope(sign: Zodiac): Flow<String> =
        flowOf("$sign is gonna have a great day!")
}

interface HoroscopeRepository {
    fun getHoroscope(sign: Zodiac): Flow<String>
}