package org.codeforegypt.wishinglist

sealed class Screen (val route: String){
    object HomeScreen: Screen("home")
    object AddScreen: Screen("add")

}