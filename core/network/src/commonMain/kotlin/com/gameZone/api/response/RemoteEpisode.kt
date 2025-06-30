package com.gameZone.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteEpisode(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("air_date") val airDate: String,
    @SerialName("episode") val episodeCode: String,
    @SerialName("characters") val characterUrls: List<String>,
    @SerialName("url") val url: String,
    @SerialName("created") val createdAt: String
)
