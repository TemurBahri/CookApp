package com.timurbahri.cookapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.timurbahri.cookapp.entity.Cooks

@Database(entities = [Cooks::class],version = 1)
abstract class Db : RoomDatabase() {
    abstract fun cooksDao():ICooksDao

    companion object{
        var INSTANCE:Db? = null

        fun dbEntity(context: Context):Db?{
            if (INSTANCE == null){

                synchronized(Db::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        Db::class.java,
                        "JetpackComposeDb.db").createFromAsset("JetpackComposeDb.db").build()
                }
            }
            return INSTANCE
        }
    }
}