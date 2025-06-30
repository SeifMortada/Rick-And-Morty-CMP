package com.gameZone.project.di

import com.gameZone.characters.di.charactersModule
import com.gameZone.di.domainModule
import com.gameZone.di.networkModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin(koinApplication: ((KoinApplication) -> Unit)? = null) {
    startKoin {
        modules(
            networkModule,
            domainModule,
            charactersModule
        )
    }
}