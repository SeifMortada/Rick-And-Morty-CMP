package com.gameZone.characters.di

import com.gameZone.characters.ui.CharactersViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {
    viewModel { CharactersViewModel(getAllCharactersUseCase = get()) }
}