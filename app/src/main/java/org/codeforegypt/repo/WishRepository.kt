package org.codeforegypt.repo

import kotlinx.coroutines.flow.Flow
import org.codeforegypt.db.WishDao
import org.codeforegypt.model.Wish

class WishRepository(private val wishDao: WishDao) {

   suspend fun addWish(wish: Wish){
        wishDao.addAWish(wish)
    }
    suspend fun delete(wish: Wish){
        wishDao.deleteWish(wish)
    }
    suspend fun updateWish(wish: Wish){
        wishDao.updateWish(wish)
    }
     fun getWishes(): Flow<List<Wish>> {
         return wishDao.getAllWishes()
     }
    fun getWishById(id: Long): Flow<Wish> {
         return wishDao.getWish(id)
    }
}