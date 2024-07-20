package uk.ac.tees.mad.D3905133.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class NavDestination(val route: String){
    object SPLASH : NavDestination("splash")
    object SIGNUP : NavDestination("signup")
    object LOGIN : NavDestination("login")
    object HOME : NavDestination("home")
    object DETAIL : NavDestination("detail")
    object SAVED : NavDestination("saved")
    object PROFILE : NavDestination("profile")
}

@Composable
fun AppNavigation(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavDestination.SPLASH.route){
        composable(route = NavDestination.SPLASH.route){
        }
        composable(route = NavDestination.SIGNUP.route){
        }
        composable(route = NavDestination.LOGIN.route){
        }
    }
}