package com.gameZone.characters.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gameZone.usecase.GetAllCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class CharactersViewModel(private val getAllCharactersUseCase: GetAllCharactersUseCase)
    : ViewModel() {
    private val _charactersUiState: MutableStateFlow<CharactersResultUiState> =
        MutableStateFlow(CharactersResultUiState.Idle)
    val charactersUiState = _charactersUiState.asStateFlow()

    init {
        getCharacters()
    }
    fun getCharacters() {
        viewModelScope.launch {
            _charactersUiState.update { CharactersResultUiState.Loading }
            val characters = getAllCharactersUseCase()
            if (characters.isEmpty()) {
                _charactersUiState.update { CharactersResultUiState.Error("Error getting characters") }
                return@launch
            }
            _charactersUiState.update { CharactersResultUiState.Success(characters) }
        }
    }
}