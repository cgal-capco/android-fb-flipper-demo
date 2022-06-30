package com.example.jetsnack.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int? = null,

    val title: String?,
    val description: String?
)