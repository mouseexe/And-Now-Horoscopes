package gay.spiders.andnowhoroscopes.repository

import gay.spiders.andnowhoroscopes.HoroscopesQuery
import gay.spiders.andnowhoroscopes.data.Zodiac
import gay.spiders.andnowhoroscopes.module.apolloClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HoroscopeRepositoryImpl @Inject constructor(): HoroscopeRepository {

    // TODO - restructure query to only grab the relevant zodiac
    override fun getHoroscope(sign: Zodiac): Flow<String> =
        apolloClient
            .query(HoroscopesQuery())
            .toFlow()
            .map { horoscopes ->
                val horoscope = horoscopes.data?.horoscopesCollection?.items?.firstOrNull()
                when (sign) {
                    Zodiac.ARIES -> horoscope?.aries
                    Zodiac.TAURUS -> horoscope?.taurus
                    Zodiac.GEMINI -> horoscope?.gemini
                    Zodiac.CANCER -> horoscope?.cancer
                    Zodiac.LEO -> horoscope?.leo
                    Zodiac.VIRGO -> horoscope?.virgo
                    Zodiac.LIBRA -> horoscope?.libra
                    Zodiac.SCORPIO -> horoscope?.scorpio
                    Zodiac.SAGITTARIUS -> horoscope?.sagittarius
                    Zodiac.CAPRICORN -> horoscope?.capricorn
                    Zodiac.AQUARIUS -> horoscope?.aquarius
                    Zodiac.PISCES -> horoscope?.pisces
                }.orEmpty()
            }
}

interface HoroscopeRepository {
    fun getHoroscope(sign: Zodiac): Flow<String>
}