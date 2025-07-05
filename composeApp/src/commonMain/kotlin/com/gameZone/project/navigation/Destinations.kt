package com.gameZone.project.navigation


import kotlinx.serialization.Serializable

sealed interface Dest {
    @Serializable
    data object Characters: Dest

    @Serializable
    data class CharacterDetails(val characterId: Int): Dest

    @Serializable
    data class EpisodeDetails(val episodeId: Int): Dest

}