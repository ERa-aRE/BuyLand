package com.example.buyland.data.local

import androidx.room.*
import com.example.buyland.domain.model.User


@Dao
interface BuylandDao {


    @Query("SELECT id FROM user WHERE userName = :userName")
    suspend fun getIdByName(userName:String):Int?

    @Query("SELECT EXISTS(SELECT 1 FROM user WHERE userName =:userName)")//this query may not work , sqlite does not support Boolean Type
    suspend fun checkUserName(userName:String): kotlin.Boolean

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById (id:Int): User

    @Query("SELECT password FROM user WHERE id=:id")
    suspend fun getPasswordById (id:Int): String


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:User)

    @Update
    suspend fun updateUser(user:User)
    ////////////////////////////////////////////////////////////////////////
    /*@Query("SELECT * FROM product where pId =:pId")
    suspend fun getProductByPId(pId:Int):Product
    @Query("SELECT * FROM product")
    fun getProducts(): Flow<List<Product>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)
    @Delete
    suspend fun deleteProduct(product:Product)*/




}