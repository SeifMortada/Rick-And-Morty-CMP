package characterDetails

import com.gameZone.models.Character
import com.gameZone.models.Episode


sealed interface CharacterResultUiState {
    data object Loading : CharacterResultUiState
    data object Idle : CharacterResultUiState
    data class Error(val message: String) : CharacterResultUiState
    data class Success(
        val characters: Character? = null,
        val episodes: List<Episode> = emptyList()
    ) : CharacterResultUiState
}