package com.gameZone.project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import characterDetails.CharacterDetailsRoute
import com.gameZone.characters.ui.CharactersRoute
import com.gameZone.episodeDetails.ui.EpisodeDetailsScreen

@Composable
fun AppNavHost() {
    val navHostController = rememberNavController()
    NavHost(navHostController, startDestination = Dest.Characters) {
        composable<Dest.Characters> {
            CharactersRoute { characterId ->
                navHostController.navigate(Dest.CharacterDetails(characterId = characterId))
            }
        }
        composable<Dest.CharacterDetails>() { backStackEntry ->
            val characterId = backStackEntry.toRoute<Dest.CharacterDetails>().characterId
            CharacterDetailsRoute(characterId = characterId) { episodeId ->
                navHostController.navigate(Dest.EpisodeDetails(episodeId = episodeId))
            }
        }
        composable<Dest.EpisodeDetails>() { backStack ->
            val episodeId = backStack.toRoute<Dest.EpisodeDetails>().episodeId
            EpisodeDetailsScreen(episodeId = episodeId)
        }
    }
}