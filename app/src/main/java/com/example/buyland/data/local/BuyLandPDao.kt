package com.example.buyland.data.local

import androidx.room.*
import com.example.buyland.domain.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface BuyLandPDao {
    @Query("SELECT * FROM product where pId =:pId")
    suspend fun getProductByPId(pId:Int):Product
    @Query("SELECT * FROM product WHERE userId=:userId")
    fun getProducts(userId:Int): Flow<List<Product>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)
    @Delete
    suspend fun deleteProduct(product: Product)
}