package uk.ac.tees.mad.D3905133.navigation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.D3905133.NewsViewModel
import uk.ac.tees.mad.D3905133.appscreen.HomeScreen
import uk.ac.tees.mad.D3905133.appscreen.LoginScreen
import uk.ac.tees.mad.D3905133.appscreen.NewsDetailScreen
import uk.ac.tees.mad.D3905133.appscreen.ProfileScreen
import uk.ac.tees.mad.D3905133.appscreen.SavedNewsScreen
import uk.ac.tees.mad.D3905133.appscreen.SignUpScreen
import uk.ac.tees.mad.D3905133.appscreen.SplashScreen

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
    Surface {
        val viewModel : NewsViewModel = viewModel()
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = NavDestination.SPLASH.route) {
            composable(route = NavDestination.SPLASH.route) {
                SplashScreen(navController = navController, vm = viewModel)
            }
            composable(route = NavDestination.SIGNUP.route) {
                SignUpScreen(navController = navController, vm = viewModel)
            }
            composable(route = NavDestination.LOGIN.route) {
                LoginScreen(navController = navController,vm = viewModel)
            }
            composable(route = NavDestination.HOME.route) {
                HomeScreen()
            }
            composable(route = NavDestination.DETAIL.route) {
                NewsDetailScreen()
            }
            composable(route = NavDestination.SAVED.route) {
                SavedNewsScreen()
            }
            composable(route = NavDestination.PROFILE.route) {
                ProfileScreen()
            }
        }
    }
}