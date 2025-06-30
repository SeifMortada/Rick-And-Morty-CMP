package com.gameZone.usecase

import com.gameZone.models.Character
import com.gameZone.repository.CharactersRepository

class GetAllCharactersUseCase (private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(): List<Character> {
        val apiResponse = charactersRepository.getCharacters()
        var result = listOf<Character>()
        apiResponse.onSuccess {
            result = it
        }.onFailure {
            result = emptyList()
        }
        return result
    }
}