package characterDetails.di

import characterDetails.CharacterDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val characterDetailsModule= module {
    viewModel { CharacterDetailsViewModel(
        getCharacterEpisodes = get(),
        getCharacterUseCase = get()
    ) }
}