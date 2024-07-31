package uk.ac.tees.mad.D3905133.appscreen


import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.D3905133.NewsViewModel
import uk.ac.tees.mad.D3905133.navigation.NavDestination
import uk.ac.tees.mad.D3905133.redColor
import uk.ac.tees.mad.D3905133.ui.theme.openSans

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(vm: NewsViewModel) {
    vm.fetchUserProfile()
    val context = LocalContext.current
    val userProfile = vm.userProfile.value
    var name by remember { mutableStateOf(userProfile?.name ?: "") }
    var location by remember { mutableStateOf(userProfile?.location ?: "") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text(
                            text = "Profile",
                            fontSize = 35.sp,
                            fontFamily = openSans,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = redColor,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box {
                Row(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Hello ${userProfile?.name}")
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "email: ${userProfile?.email}")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(modifier = Modifier.padding(top = 300.dp).align(Alignment.Center)) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Name") })
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = location,
                        onValueChange = { location = it },
                        label = { Text("Location") })
                    Spacer(modifier = Modifier.height(10.dp))

                }
                Button(onClick = {
                    if (name.isEmpty() || location.isEmpty()) {
                        Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    }else {
                        vm.updateUserProfile(context , name, location)
                    } }, modifier = Modifier.padding(top = 500.dp).align(Alignment.Center)) {
                    Text(text = "Save User")
                }
            }
        }
    }
}

