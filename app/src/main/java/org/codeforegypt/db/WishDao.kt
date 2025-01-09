package org.codeforegypt.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.codeforegypt.model.Wish

@Dao
abstract class WishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addAWish(wishEntity: Wish)

    @Update
    abstract suspend fun updateWish(wishEntity: Wish)

    @Delete
    abstract suspend fun deleteWish(wishEntity: Wish)

    @Query("SELECT * FROM 'MyWishes' ")
    abstract fun getAllWishes(): Flow<List<Wish>>

    @Query("SELECT * FROM 'MyWishes' WHERE  id=:id")
    abstract fun getWish(id: Long): Flow<Wish>

}