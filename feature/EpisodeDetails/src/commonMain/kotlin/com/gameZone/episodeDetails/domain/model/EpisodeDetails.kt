package com.gameZone.episodeDetails.domain.model

import com.gameZone.models.Character
import com.gameZone.models.Episode

data class EpisodeDetails(
    val episode: Episode,
    val characters: List<Character>
)
