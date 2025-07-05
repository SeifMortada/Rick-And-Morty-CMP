package com.gameZone.episodeDetails.ui

import androidx.lifecycle.ViewModel
import com.gameZone.episodeDetails.domain.model.EpisodeDetails
import com.gameZone.episodeDetails.domain.usecase.GetEpisodeDetailsUseCase
import com.gameZone.models.ApiOperation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EpisodeDetailsViewModel(private val getEpisodeDetailsUseCase: GetEpisodeDetailsUseCase):
    ViewModel() {

    private val _episodeDetailsState =
        MutableStateFlow<EpisodeDetailsState>(EpisodeDetailsState.Loading)
    val episodeDetailsState = _episodeDetailsState.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    fun fetchEpisodeDetails(episodeId: String) = viewModelScope.launch {
        _episodeDetailsState.value = EpisodeDetailsState.Loading
        val result = runCatching { getEpisodeDetailsUseCase(episodeId) }
        _episodeDetailsState.update {
            result.fold(
                onSuccess = { apiOperation ->
                    when (apiOperation) {
                        is ApiOperation.Success -> EpisodeDetailsState.Success(apiOperation.data)
                        is ApiOperation.Failure -> EpisodeDetailsState.Error(
                            apiOperation.exception.message ?: "An error occurred"
                        )
                    }
                },
                onFailure = { throwable ->
                    EpisodeDetailsState.Error(throwable.message ?: "An error occurred")
                }
            )
        }
    }
}

sealed interface EpisodeDetailsState {
    object Loading : EpisodeDetailsState
    data class Success(val details: EpisodeDetails) : EpisodeDetailsState
    data class Error(val message: String) : EpisodeDetailsState
}