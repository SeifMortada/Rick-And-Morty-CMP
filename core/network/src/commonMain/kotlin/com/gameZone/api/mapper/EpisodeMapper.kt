package com.gameZone.api.mapper

import com.gameZone.api.response.RemoteEpisode
import com.gameZone.models.Episode

fun RemoteEpisode.toDomainEpisode(): Episode {
    return Episode(
        id = id,
        name = name,
        episode = episodeCode,
        characters = characterUrls
    )
}