package com.timurbahri.cookapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "cooks")
data class Cooks(@PrimaryKey(autoGenerate = true)
                 @ColumnInfo (name = "cook_id") @NotNull var cook_id:Int,
                 @ColumnInfo (name ="cook_name") @NotNull var cook_name:String,
                 @ColumnInfo (name = "cook_image_name") @NotNull var cook_image_name:String,
                 @ColumnInfo (name = "cook_price") @NotNull var cook_price:Int) {

}