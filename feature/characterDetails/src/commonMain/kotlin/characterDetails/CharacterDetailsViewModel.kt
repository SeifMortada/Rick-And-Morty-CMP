package characterDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gameZone.models.Character
import com.gameZone.usecase.GetCharacterEpisodes
import com.gameZone.usecase.GetCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch



class CharacterDetailsViewModel (
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getCharacterEpisodes: GetCharacterEpisodes
) : ViewModel() {
    private val _characterUiState: MutableStateFlow<CharacterResultUiState> =
        MutableStateFlow(CharacterResultUiState.Idle)
    val characterUiState = _characterUiState.asStateFlow()

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            _characterUiState.update { CharacterResultUiState.Loading }
            val character = getCharacterUseCase(id)
            if (character == null) {
                _characterUiState.update { CharacterResultUiState.Error("Error getting character") }
                return@launch
            }
            getCharacterEpisodes(character)
        }
    }

    private fun getCharacterEpisodes(character: Character) {
        viewModelScope.launch {
            val episodes = getCharacterEpisodes(character.episodesUrl)
            episodes.onSuccess { episodes ->
                _characterUiState.update { CharacterResultUiState.Success(character, episodes) }
            }
                .onFailure { error ->
                    _characterUiState.update { CharacterResultUiState.Error("Error getting episodes ${error.message}") }
                }
        }
    }
}