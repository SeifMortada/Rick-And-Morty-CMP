package com.gameZone.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.gameZone.project.di.initKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "GameZone",
    ) {
        initKoin()
        App()
    }
}