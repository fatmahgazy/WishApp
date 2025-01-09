package org.codeforegypt.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "MyWishes")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    @ColumnInfo("WishTitle")
    val title: String,
    @ColumnInfo("WishDescription")
    val description: String
){
companion object {
    fun dummyData(): List<Wish>{
                return listOf(
                    Wish(
                        id = 1,
                        title = "MyWish this year 2025",
                        description = "Focus on your dream"
            ),
                    Wish(
                        id = 1,
                        title = "MyWish this year 2025",
                        description = "Focus on your dream"
                    ),
                    Wish(
                        id = 1,
                        title = "MyWish this year 2025",
                        description = "Focus on your dream"
                    ),
                    Wish(
                        id = 1,
                        title = "MyWish this year 2025",
                        description = "Focus on your dream"
                    ),
        )

    }    }
}