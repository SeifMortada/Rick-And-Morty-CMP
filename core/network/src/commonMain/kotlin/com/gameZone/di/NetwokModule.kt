package com.gameZone.di

import com.gameZone.api.service.ApiService
import com.gameZone.client.KtorClient
import com.gameZone.repository.CharactersRepository
import com.gameZone.repository.RemoteCharactersRepository
import org.koin.dsl.module

val networkModule= module {
    single { KtorClient.getInstance() }
    single { ApiService(httpClient = get()) }
    factory<CharactersRepository> { RemoteCharactersRepository(apiService = get()) }
}