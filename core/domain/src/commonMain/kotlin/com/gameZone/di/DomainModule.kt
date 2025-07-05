package com.gameZone.di

import com.gameZone.usecase.GetAllCharactersUseCase
import com.gameZone.usecase.GetCharacterEpisodes
import com.gameZone.usecase.GetCharacterUseCase
import org.koin.dsl.module

val domainModule= module {
    factory { GetAllCharactersUseCase(charactersRepository = get()) }
    factory { GetCharacterUseCase(charactersRepository = get()) }
    factory { GetCharacterEpisodes(charactersRepository = get()) }
}