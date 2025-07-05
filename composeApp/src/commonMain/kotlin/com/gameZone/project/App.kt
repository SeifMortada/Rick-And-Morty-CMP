package com.gameZone.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import characterDetails.CharacterDetailsRoute
import com.gameZone.characters.ui.CharactersRoute
import com.gameZone.episodeDetails.ui.EpisodeDetailsScreen
import com.gameZone.project.navigation.AppNavHost
import com.gameZone.project.navigation.Dest
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        AppNavHost()
    }
}