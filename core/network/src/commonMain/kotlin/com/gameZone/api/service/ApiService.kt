package com.gameZone.api.service

import com.gameZone.api.mapper.toDomainCharacter
import com.gameZone.api.mapper.toDomainEpisode
import com.gameZone.models.ApiOperation
import com.gameZone.api.response.AllCharactersResponse
import com.gameZone.api.response.RemoteCharacter
import com.gameZone.api.response.RemoteEpisode
import com.gameZone.models.Character
import com.gameZone.models.Episode
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class ApiService (private val httpClient: HttpClient){

    suspend fun getCharacters(): ApiOperation<List<Character>> {
        return safeApiCall {
            httpClient.get("api/character")
                .body<AllCharactersResponse>()
                .results.map { it.toDomainCharacter() }
        }
    }
     suspend fun getCharacter(id: Int): ApiOperation<Character> {
        return safeApiCall {
            httpClient.get("api/character/$id")
                .body<RemoteCharacter>()
                .toDomainCharacter()
        }
    }

     suspend fun getCharacterEpisodes(episodesUrl: List<String>): ApiOperation<List<Episode>> {
        return safeApiCall {
            val episodesTrimmed = episodesUrl.map { url ->
                url.substringAfterLast("/")
            }
            coroutineScope {
                val remoteEpisodes = episodesTrimmed.map { episodeId ->
                    async { httpClient.get(urlString = "api/episode/$episodeId").body<RemoteEpisode>() }
                }
                remoteEpisodes.awaitAll().map { it.toDomainEpisode() }
            }
        }
    }
}
private inline fun <T> safeApiCall(apiCall: () -> T): ApiOperation<T> {
    return try {
        ApiOperation.Success(apiCall())
    } catch (e: Exception) {
        ApiOperation.Failure(e)
    }

}