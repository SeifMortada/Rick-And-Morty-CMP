package com.gameZone.models

sealed class CharacterStatus( val displayName: String,  val color: StatusColor) {
    data object Alive : CharacterStatus("Alive", StatusColor.GREEN)
    data object Dead : CharacterStatus("Dead", StatusColor.RED)
    data object Unknown : CharacterStatus("Unknown", StatusColor.YELLOW)

}