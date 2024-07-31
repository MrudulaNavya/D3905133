package uk.ac.tees.mad.D3905133.appscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import uk.ac.tees.mad.D3905133.R
import uk.ac.tees.mad.D3905133.redColor
import uk.ac.tees.mad.D3905133.ui.theme.openSans

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(title: String?, description: String?, author: String?, published: String?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Details",
                        fontSize = 35.sp,
                        fontFamily = openSans,
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = redColor,
                    titleContentColor = Color.White
                )
            )
        }
    ) { iv ->
        Column(
            modifier = Modifier
                .padding(iv)
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.size(300.dp)) {
                val painter = rememberAsyncImagePainter(
                    model = R.drawable.designer__1_,
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    error = painterResource(R.drawable.ic_launcher_foreground)
                )
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Column {
                Card(modifier = Modifier.fillMaxSize(), shape = MaterialTheme.shapes.medium) {
                    Column {
                        Text(
                            text = published.toString(),
                            fontFamily = openSans,
                            modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                        )
                        Text(
                            text = title.toString(),
                            fontFamily = openSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                        )
                        Text(
                            text = "published by - ${author.toString()}",
                            fontFamily = openSans,
                            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                        )
                        if (description != null) {
                            Text(
                                text = description.toString(),
                                fontFamily = openSans,
                                modifier = Modifier.padding(start = 10.dp, top = 5.dp))
                        }
                        Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(10.dp)) {
                            Text("for more details click here")
                        }
                    }
                }
            }
        }
    }
}