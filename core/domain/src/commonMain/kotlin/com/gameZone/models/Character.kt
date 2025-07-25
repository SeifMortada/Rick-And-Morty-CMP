package com.gameZone.models

data class Character(
    val created: String,
    val episodesUrl: List<String>,
    val gender: CharacterGender,
    val id: Int,
    val imageUrl: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: CharacterStatus,
    val type: String
) {
    data class Location(
        val name: String,
        val url:String
    )
    data class Origin(
        val name:String,
        val url:String
    )
}
