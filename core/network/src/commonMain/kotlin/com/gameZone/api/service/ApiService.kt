package com.gameZone.api.service

import com.gameZone.api.mapper.toDomainCharacter
import com.gameZone.models.ApiOperation
import com.gameZone.api.response.AllCharactersResponse
import com.gameZone.api.response.RemoteCharacter
import com.gameZone.models.Character
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService (private val httpClient: HttpClient){

    suspend fun getCharacters(): ApiOperation<List<Character>> {
        return safeApiCall {
            httpClient.get("api/character")
                .body<AllCharactersResponse>()
                .results.map { it.toDomainCharacter() }
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