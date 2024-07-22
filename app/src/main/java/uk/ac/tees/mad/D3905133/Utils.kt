package uk.ac.tees.mad.D3905133

import androidx.navigation.NavController
import uk.ac.tees.mad.D3905133.navigation.NavDestination

fun navigateWithNoBackStack(navController: NavController, destination: NavDestination){
    navController.navigate(destination.route){
        popUpTo(0)
    }
}

fun navigateWithBackStack(navController: NavController, destination: NavDestination){
    navController.navigate(destination.route)
}