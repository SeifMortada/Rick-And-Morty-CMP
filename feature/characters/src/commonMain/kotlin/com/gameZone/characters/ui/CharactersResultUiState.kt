package com.gameZone.characters.ui

import com.gameZone.models.Character


sealed interface CharactersResultUiState {
    data object Loading : CharactersResultUiState
    data object Idle : CharactersResultUiState
    data class Error(val message: String) : CharactersResultUiState
    data class Success(val characters: List<Character> = emptyList()) : CharactersResultUiState
}