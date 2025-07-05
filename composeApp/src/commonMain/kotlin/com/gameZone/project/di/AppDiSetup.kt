package com.gameZone.project.di

import com.gameZone.characters.di.charactersModule
import com.gameZone.di.domainModule
import com.gameZone.di.networkModule
import com.gameZone.episodeDetails.di.episodeDetailsModule
import characterDetails.di.characterDetailsModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin(koinApplication: ((KoinApplication) -> Unit)? = null) {
    startKoin {
        modules(
            networkModule,
            domainModule,
            charactersModule,
            characterDetailsModule,
            episodeDetailsModule
        )
    }
}