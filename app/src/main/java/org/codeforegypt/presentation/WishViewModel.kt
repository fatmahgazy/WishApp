package org.codeforegypt.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.codeforegypt.db.Graph
import org.codeforegypt.model.Wish
import org.codeforegypt.repo.WishRepository

class WishViewModel(
    private val wishRepo: WishRepository = Graph.wishRepository
): ViewModel() {
    var wishTitleState by mutableStateOf("")
     var wishDescriptionState by mutableStateOf("")

    fun wishTitleChanged(newData: String){
        wishTitleState = newData
    }
    fun wishDescriptionChanged(newData: String){
        wishDescriptionState = newData
    }
    var getAllWishes: Flow<List<Wish>> = wishRepo.getWishes()

    fun addWish(addAWish: Wish){
        viewModelScope.launch {
            wishRepo.addWish(addAWish)
        }
    }
    fun resetWish(){
       wishTitleState = ""
       wishDescriptionState = ""
    }
    fun deleteWish(deleteWish: Wish){
        viewModelScope.launch {
            wishRepo.delete(deleteWish)
        }
    }
    fun updateWish(updateWish: Wish){
        viewModelScope.launch {
            wishRepo.updateWish(updateWish)
        }
    }
    fun getWishById(wishId: Long): Flow<Wish>{
            return wishRepo.getWishById(wishId)
                .onEach {
                   if(wishId != 0L){
                       wishTitleState = it.title
                       wishDescriptionState = it.description
                   }
                }
    }
}