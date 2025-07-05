package com.gameZone.usecase

import com.gameZone.models.Character
import com.gameZone.repository.CharactersRepository

class GetCharacterUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(id: Int): Character? {
        var result: Character? = null
        val apiResponse = charactersRepository.getCharacter(id)
        apiResponse.onSuccess {
            result = it
        }.onFailure {
            result = null
        }
        return result
    }
}
