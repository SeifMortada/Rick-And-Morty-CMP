package com.gameZone.project.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import characterDetails.CharacterDetailsRoute
import com.gameZone.characters.ui.CharactersRoute
import com.gameZone.episodeDetails.ui.EpisodeDetailsScreen

object CharactersNavGraph : BaseNavGraph {
    
    sealed class Dest(val route: String) {
        data object Characters : Dest("/characters")
        data object Route : Dest("/characters-route")
        data object CharacterDetails : Dest("/character_details/{id}") {
            fun getRoute(id: Int) = "/character_details/${id}"
        }
        data object EpisodeDetails : Dest("/episode_details/{id}") {
            fun getRoute(id: Int) = "/episode_details/${id}"
        }
    }

    override fun build(
        modifier: Modifier,
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            route = Dest.Route.route,
            startDestination = Dest.Characters.route
        ) {
            composable(route = Dest.Characters.route) {
                CharactersRoute { characterId ->
                    navHostController.navigate(Dest.CharacterDetails.getRoute(id = characterId))
                }
            }
            composable(
                route = Dest.CharacterDetails.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val characterId = backStackEntry.arguments?.getInt("id") ?: -1
                CharacterDetailsRoute(characterId = characterId){ episodeId ->
                    navHostController.navigate(Dest.EpisodeDetails.getRoute(id = episodeId))
                }
            }
            composable(
                route = Dest.EpisodeDetails.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) {
                val episodeId = it.arguments?.getInt("id") ?: -1
                EpisodeDetailsScreen(episodeId = episodeId)
            }
        }

    }
}