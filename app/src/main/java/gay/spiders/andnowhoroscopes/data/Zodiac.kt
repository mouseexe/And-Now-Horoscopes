package gay.spiders.andnowhoroscopes.data

import androidx.compose.ui.graphics.Color
import gay.spiders.andnowhoroscopes.R

enum class Zodiac {
    ARIES,
    TAURUS,
    GEMINI,
    CANCER,
    LEO,
    VIRGO,
    LIBRA,
    SCORPIO,
    SAGITTARIUS,
    CAPRICORN,
    AQUARIUS,
    PISCES;

    fun getName(): String = this.toString().lowercase().replaceFirstChar { this.toString().first() }

    fun getColor(): Color = when (this) {
        ARIES -> Color(0xFFA50001)
        TAURUS -> Color(0xFFA14F00)
        GEMINI -> Color(0xFFA1A100)
        CANCER -> Color(0xFF808080)
        LEO -> Color(0xFF406600)
        VIRGO -> Color(0xFF028141)
        LIBRA -> Color(0xFF008282)
        SCORPIO -> Color(0xFF015681)
        SAGITTARIUS -> Color(0xFF000056)
        CAPRICORN -> Color(0xFF290055)
        AQUARIUS -> Color(0xFF7F006E)
        PISCES -> Color(0xFF7F0037)
    }

    fun getIcon(): Int = when (this) {
        ARIES -> R.drawable.ic_aries
        TAURUS -> R.drawable.ic_taurus
        GEMINI -> R.drawable.ic_gemini
        CANCER -> R.drawable.ic_cancer
        LEO -> R.drawable.ic_leo
        VIRGO -> R.drawable.ic_virgo
        LIBRA -> R.drawable.ic_libra
        SCORPIO -> R.drawable.ic_scorpio
        SAGITTARIUS -> R.drawable.ic_sagittarius
        CAPRICORN -> R.drawable.ic_capricorn
        AQUARIUS -> R.drawable.ic_aquarius
        PISCES -> R.drawable.ic_pisces
    }
}