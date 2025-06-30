package com.gameZone.project

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.gameZone.project.navigation.CharactersNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        val navHostController = rememberNavController()
        NavHost(navHostController, startDestination = CharactersNavGraph.Dest.Route.route) {
            listOf(CharactersNavGraph)
                .forEach {
                    it.build(
                        modifier = Modifier.fillMaxSize(),
                        navHostController = navHostController,
                        navGraphBuilder = this
                    )
                }
        }
    }
}