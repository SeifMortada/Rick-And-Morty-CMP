package com.gameZone.episodeDetails.di

import com.gameZone.episodeDetails.domain.usecase.GetEpisodeDetailsUseCase
import com.gameZone.episodeDetails.ui.EpisodeDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val episodeDetailsModule = module {
    single { GetEpisodeDetailsUseCase(charactersRepository = get(), getCharacterUseCase = get()) }
    viewModel { EpisodeDetailsViewModel(getEpisodeDetailsUseCase = get()) }
}