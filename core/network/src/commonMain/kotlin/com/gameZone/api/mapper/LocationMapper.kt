package com.gameZone.api.mapper

import com.gameZone.api.response.RemoteCharacter
import com.gameZone.models.Character.Location

fun RemoteCharacter.RemoteLocation.toDomainLocation():  Location {
    return Location(
        name = name,
        url = url
    )
}