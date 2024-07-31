package uk.ac.tees.mad.D3905133.appscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uk.ac.tees.mad.D3905133.NewsViewModel
import uk.ac.tees.mad.D3905133.navigation.NavDestination
import uk.ac.tees.mad.D3905133.redColor
import uk.ac.tees.mad.D3905133.ui.theme.openSans

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedNewsScreen(navController: NavController, vm: NewsViewModel) {
    vm.getFavoriteNews()
        val news = vm.favoriteNews.collectAsState(initial = emptyList())
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Favorite News",
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
            },
            floatingActionButton = {
                Icon(
                    imageVector = Icons.Rounded.Favorite,
                    contentDescription = null,
                    tint = redColor,
                    modifier = Modifier
                        .padding(20.dp)
                        .size(60.dp)
                        .clickable {

                        }
                )
            }
        ) { innerPadding ->

            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(news.value){ item ->
                    NewsView(title = item?.title, description = item?.description, author = item?.author, published = item?.published, onDeleteClick = {
                            vm.deleteNews(item!!)
                    }, onCardClick = {
                        navController.navigate(NavDestination.DETAIL.createRoute(
                            item?.title!!,
                            item?.description,
                            item?.author,
                            item?.published
                        ))
                    })
                }
            }
        }
    }

    @Composable
    fun NewsView( title: String?, description: String?, author: String?, published : String?, onCardClick:() -> Unit, onDeleteClick:()-> Unit) {
        Card(elevation = CardDefaults.elevatedCardElevation(10.dp), modifier = Modifier
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .clickable {
                onCardClick()
            }) {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(
                    text = title.toString(),
                    fontSize = 10.sp,
                    fontFamily = openSans,
                    fontWeight = FontWeight.Bold
                )

                Row {
                    if (author != null) {
                        Text(text = author,
                            fontSize = 8.sp,
                            fontFamily = openSans)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    if (published != null) {
                        Text(text = published,
                            fontSize = 8.sp,
                            fontFamily = openSans
                        )
                    }
                    Icon(imageVector = Icons.Rounded.Delete, contentDescription = null
                        , modifier = Modifier.padding(start = 5.dp).clickable {
                            onDeleteClick()
                        })
                }

            }
        }
}