package com.example.jetsnack.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.jetsnack.database.entities.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAll(): List<ProductEntity>

    @Query("SELECT * FROM products WHERE uid IN (:productIds)")
    fun loadAllByIds(productIds: IntArray): List<ProductEntity>

    @Query(
        "SELECT * FROM products WHERE title LIKE :title"
    )
    fun findByTitle(title: String): ProductEntity

    @Insert
    fun insertAll(vararg products: ProductEntity)

    @Delete
    fun delete(product: ProductEntity)

    @Query("DELETE FROM products")
    fun clearTable()
}
