package org.codeforegypt.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
    ) {
        val listData = viewModel.getAllWishes.collectAsState(initial = emptyList())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(listData.value){ wish ->
                WishList(
                    wish = wish
                ) {
                    val id  = wish.id
                    navController.navigate(Screen.AddScreen.route + "/$id")
                }
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
        onClick = onClick ) {
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
        Spacer(modifier = Modifier.height(20.dp))

    }
}