package com.example.jetsnack.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetsnack.database.daos.ProductDao
import com.example.jetsnack.database.daos.UserDao
import com.example.jetsnack.database.entities.ProductEntity
import com.example.jetsnack.database.entities.UserEntity

@Database(
    entities = [
        UserEntity::class,
        ProductEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
}