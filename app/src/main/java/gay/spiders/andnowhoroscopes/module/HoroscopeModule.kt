package gay.spiders.andnowhoroscopes.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import gay.spiders.andnowhoroscopes.repository.HoroscopeRepository
import gay.spiders.andnowhoroscopes.repository.HoroscopeRepositoryImpl

@InstallIn(ViewModelComponent::class)
@Module
interface HoroscopeModule {

    @Binds
    fun bindHoroscopeRepository(repository: HoroscopeRepositoryImpl): HoroscopeRepository
}