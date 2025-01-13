package org.codeforegypt.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.sp
import org.codeforegypt.wishinglist.Screen
import org.codeforegypt.model.Wish


@Composable
fun HomeScreen(
    viewModel: WishViewModel = viewModel(),
    navController: NavController
) {

    val showBackIcon = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AppBar(
                title = "Wishlist",
                onBackNavClick = { if (showBackIcon.value) navController.navigateUp() })
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                onClick = {
                    viewModel.resetWish()
                    navController.navigate(Screen.AddScreen.route + "/0L")
                },
                containerColor = Color.Black,
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
    ) { it ->
        val listData = viewModel.getAllWishes.collectAsState(initial = emptyList())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(listData.value , key = {wish -> wish.title}){
                wish ->
                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = {
                        if (it == SwipeToDismissBoxValue.StartToEnd || it == SwipeToDismissBoxValue.EndToStart){
                            viewModel.deleteWish(wish)
                        }
                        true
                    }
                )

                SwipeToDismissBox(
                    state = dismissState,
                    enableDismissFromStartToEnd = false,
                    backgroundContent = {
                        val color by animateColorAsState(
                            if (dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart) Color.Red else Color.Transparent
                                ,label = ""
                                )
                    val align = Alignment.CenterEnd
                    Box(
                     Modifier.fillMaxSize().background(color).padding(horizontal =  20.dp),
                        contentAlignment = align
                        ){
                        Icon(
                         Icons.Default.Delete,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                    },
                    content = {
                        WishList(
                            wish = wish
                        ) {
                            val id  = wish.id
                            navController.navigate(Screen.AddScreen.route + "/$id")
                        }
                    }
                )

            }
        }
    }
}

@Composable
fun WishList(wish: Wish, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = wish.title,
                style = TextStyle(fontWeight = FontWeight.Bold))
            Text(
                text = wish.description
                , style = TextStyle(fontWeight = FontWeight.Medium)
            )
        }


    }
}