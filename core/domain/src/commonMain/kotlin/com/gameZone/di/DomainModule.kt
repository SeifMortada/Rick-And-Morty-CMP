package com.gameZone.di

import com.gameZone.usecase.GetAllCharactersUseCase
import org.koin.dsl.module

val domainModule= module {
    factory { GetAllCharactersUseCase(charactersRepository = get()) }
}