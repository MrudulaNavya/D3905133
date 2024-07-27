package uk.ac.tees.mad.D3905133.appscreen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uk.ac.tees.mad.D3905133.NewsViewModel
import uk.ac.tees.mad.D3905133.navigateWithBackStack
import uk.ac.tees.mad.D3905133.navigateWithNoBackStack
import uk.ac.tees.mad.D3905133.navigation.NavDestination
import uk.ac.tees.mad.D3905133.redColor
import uk.ac.tees.mad.D3905133.ui.theme.openSans

@Composable
fun LoginScreen(navController: NavController, vm: NewsViewModel) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val context = LocalContext.current
    val isSignedIn = vm.isSignedIn
    val isLoading = vm.isLoading

    if (isSignedIn.value) {
        navigateWithNoBackStack(navController = navController, destination = NavDestination.HOME)
    }
    Box {
        Column {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(300.dp)
                    .background(redColor)

            ) {

            }
            Column(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = "Sign in",
                        fontFamily = openSans,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(text = "Email",fontFamily = openSans)
                    TextField(
                        value = email.value,
                        onValueChange = { email.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        maxLines = 1,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Gray,
                            unfocusedBorderColor = Color.Gray
                        ),
                        label = {
                            Row {
                                Icon(
                                    imageVector = Icons.Rounded.MailOutline,
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.size(10.dp))
                                Text("email address",fontFamily = openSans)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Password",fontFamily = openSans)
                    TextField(
                        value = password.value,
                        onValueChange = { password.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        maxLines = 1,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Gray,
                            unfocusedBorderColor = Color.Gray
                        ), label = {
                            Row {
                                Icon(imageVector = Icons.Rounded.Lock, contentDescription = null)
                                Spacer(modifier = Modifier.size(10.dp))
                                Text("password",fontFamily = openSans)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(70.dp))
                    Button(
                        onClick = { vm.logIn(context, email.value, password.value) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            redColor
                        )
                    ) {
                        if (isLoading.value) {
                            CircularProgressIndicator(modifier = Modifier.size(20.dp))
                        } else {
                            Text(
                                text = "Login",
                                fontFamily = openSans,
                                color = Color.White,
                                fontSize = 15.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        Text(text = "Don't have an Account ?",fontFamily = openSans)
                        Text(text = "Sign Up",fontFamily = openSans, color = redColor, fontWeight = FontWeight.Bold, modifier = Modifier.clickable {
                            navigateWithBackStack(navController = navController, destination = NavDestination.SIGNUP)
                        })
                    }
                }
            }
        }
    }
}