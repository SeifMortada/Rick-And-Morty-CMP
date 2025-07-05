package com.gameZone.usecase

import com.gameZone.repository.CharactersRepository

class GetCharacterEpisodes(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(episodesUrl: List<String>) = charactersRepository.getCharacterEpisodes(episodesUrl)
}
