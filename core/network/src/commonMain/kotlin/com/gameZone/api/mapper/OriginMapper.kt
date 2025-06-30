package com.gameZone.api.mapper

import com.gameZone.api.response.RemoteCharacter
import com.gameZone.models.Character.Origin

fun RemoteCharacter.RemoteOrigin.toDomainOrigin():Origin {
    return Origin(
        name = name,
        url = url
    )
}