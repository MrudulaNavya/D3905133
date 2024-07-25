package uk.ac.tees.mad.D3905133.appscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import uk.ac.tees.mad.D3905133.NewsViewModel
import uk.ac.tees.mad.D3905133.R
import uk.ac.tees.mad.D3905133.checkSignedIn
import uk.ac.tees.mad.D3905133.navigateWithNoBackStack
import uk.ac.tees.mad.D3905133.navigation.NavDestination
import uk.ac.tees.mad.D3905133.ui.theme.openSans

@Composable
fun SplashScreen(navController: NavController,vm:NewsViewModel) {
    LaunchedEffect(key1 = true) {
        while (true){
            delay(2000)
            checkSignedIn(navController,vm)
            navigateWithNoBackStack(navController = navController, destination = NavDestination.LOGIN)
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.designer__1_),
            contentDescription = "Splash Screen Image",
            modifier = Modifier
                .size(250.dp)
                .clip(RoundedCornerShape(150.dp))
        )
        Text(
            text = "News Droid",
            fontFamily = openSans,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}