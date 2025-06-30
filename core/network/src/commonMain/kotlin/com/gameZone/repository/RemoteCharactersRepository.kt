package com.gameZone.repository

import com.gameZone.api.mapper.toDomainCharacter
import com.gameZone.models.ApiOperation
import com.gameZone.api.service.ApiService
import com.gameZone.models.Character

class RemoteCharactersRepository(private val apiService: ApiService) :
    CharactersRepository {
    override suspend fun getCharacters():  ApiOperation<List<Character>> {
        return apiService.getCharacters()
    }
/*
    override suspend fun getCharacter(id: Int): ApiOperation<Character> {
        return safeApiCall {
            ktorClient.get("character/$id")
                .body<com.seifmortada.applications.data.models.RemoteCharacter>()
                .toDomainCharacter()
        }
    }

    override suspend fun getCharacterEpisodes(episodesUrl: List<String>): ApiOperation<List<Episode>> {
        return safeApiCall {
            val episodesTrimmed = episodesUrl.map { url ->
                url.substringAfterLast("/")
            }
            coroutineScope {
                val remoteEpisodes = episodesTrimmed.map { episodeId ->
                    async { ktorClient.get(urlString = "episode/$episodeId").body<RemoteEpisode>() }
                }
                remoteEpisodes.awaitAll().map { it.toDomainEpisode() }
            }
        }
    }*/
}