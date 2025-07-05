package com.gameZone.episodeDetails.domain.usecase

import com.gameZone.episodeDetails.domain.model.EpisodeDetails
import com.gameZone.models.ApiOperation
import com.gameZone.repository.CharactersRepository
import com.gameZone.usecase.GetCharacterUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class GetEpisodeDetailsUseCase(
    private val charactersRepository: CharactersRepository,
    private val getCharacterUseCase: GetCharacterUseCase
) {
    suspend operator fun invoke(episodeId: String): ApiOperation<EpisodeDetails> {
        return try {
            val episodeResult = charactersRepository.getEpisodeDetails(episodeId)
            when (episodeResult) {
                is ApiOperation.Failure -> ApiOperation.Failure(episodeResult.exception)
                is ApiOperation.Success -> {
                    coroutineScope {
                        val episode = episodeResult.data
                        val characterIds = episode.characters.map { url ->
                            url.substringAfterLast("/").toIntOrNull()
                        }
                        val characterDeferred = characterIds.map { id ->
                            async { getCharacterUseCase(id = id ?: 0) }
                        }
                        val characters = characterDeferred.awaitAll().filterNotNull()
                        ApiOperation.Success(EpisodeDetails(episode, characters))
                    }
                }
            }
        } catch (e: Exception) {
            ApiOperation.Failure(e)
        }
    }
}