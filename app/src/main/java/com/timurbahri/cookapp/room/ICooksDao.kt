package com.timurbahri.cookapp.room

import androidx.room.Dao
import androidx.room.Query
import com.timurbahri.cookapp.entity.Cooks

@Dao
interface ICooksDao {
    @Query("SELECT * FROM cooks")
    suspend fun allCooks():List<Cooks>
}