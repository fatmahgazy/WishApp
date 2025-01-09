package org.codeforegypt.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import org.codeforegypt.model.Wish

@Database(
    entities = [Wish::class],
    version = 2,
    exportSchema = false

)
abstract class WishDatabase: RoomDatabase(){
    abstract fun dao() : WishDao

}