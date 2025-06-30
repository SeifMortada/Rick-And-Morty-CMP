package com.gameZone.api.response

import kotlinx.serialization.Serializable

@Serializable
data class RemoteCharacter(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: RemoteLocation,
    val name: String,
    val origin: RemoteOrigin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) {
    @Serializable
    data class RemoteLocation(
        val name: String,
        val url: String
    )

    @Serializable
    data class RemoteOrigin(
        val name: String,
        val url: String
    )
}
