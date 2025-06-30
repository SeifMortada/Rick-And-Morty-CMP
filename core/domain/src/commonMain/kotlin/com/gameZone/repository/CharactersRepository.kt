package com.gameZone.repository

import com.gameZone.models.ApiOperation
import com.gameZone.models.Character
import com.gameZone.models.Episode

interface CharactersRepository {
    suspend fun getCharacters(): ApiOperation<List<Character>>
   // suspend fun getCharacter(id: Int): ApiOperation<Character>
   // suspend fun getCharacterEpisodes(episodesUrl: List<String>): ApiOperation<List<Episode>>
}