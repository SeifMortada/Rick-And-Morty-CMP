package com.gameZone.repository

import com.gameZone.models.ApiOperation
import com.gameZone.api.service.ApiService
import com.gameZone.models.Character
import com.gameZone.models.Episode

class RemoteCharactersRepository(private val apiService: ApiService) :
    CharactersRepository {
    override suspend fun getCharacters():  ApiOperation<List<Character>> {
        return apiService.getCharacters()
    }
    override suspend fun getCharacter(id: Int): ApiOperation<Character> {
        return apiService.getCharacter(id)
    }

    override suspend fun getCharacterEpisodes(episodesUrl: List<String>): ApiOperation<List<Episode>> {
        return apiService.getCharacterEpisodes(episodesUrl)
    }
}