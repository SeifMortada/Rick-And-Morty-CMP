package com.gameZone.project.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gameZone.characters.ui.CharactersRoute

object CharactersNavGraph : BaseNavGraph {

    sealed class Dest(val route: String) {
        data object Characters : Dest("/characters")
        data object Route : Dest("/characters-route")
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
            composable (route = Dest.Characters.route){
                CharactersRoute {}
            }
        }

    }
}