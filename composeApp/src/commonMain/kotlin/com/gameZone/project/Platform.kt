package com.gameZone.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform