package org.codeforegypt.db

import android.content.Context
import androidx.room.Room
import org.codeforegypt.repo.WishRepository

object Graph{
    lateinit var database: WishDatabase

    val wishRepository by lazy {
        WishRepository(wishDao = database.dao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, WishDatabase::class.java, "Wishes").fallbackToDestructiveMigration().build()
    }
}