package org.codeforegypt.presentation

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import org.codeforegypt.wishinglist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title:String,
    onBackNavClick: () -> Unit
) {
    val navIcon : (@Composable () -> Unit) ={
        if (!title.contains("Wishlist")){
            IconButton(onClick = {onBackNavClick()}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }

        }else{
            null
        }

    }
    Surface(
        shadowElevation = 4.dp
    ) {
        TopAppBar(
            title = {
                Text(
                    text = title, color = colorResource(id = R.color.white) ,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .heightIn(24.dp)

                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colorResource(id = R.color.app_bar_color)
            ),
            navigationIcon = navIcon

        )
    }
}