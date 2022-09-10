package com.example.buyland.domain.repository

import com.example.buyland.domain.model.*
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response

interface BuylandRepository {

    suspend fun getIdByName(userName:String):Int?

    suspend fun checkUserName(userName:String):Boolean

    suspend fun getUserById (id:Int):User

    suspend fun getPasswordById(id:Int):String

    suspend fun insertUser(user: User)

    suspend fun updateUser(user: User)



    fun getProducts(userId:Int): Flow<List<Product>>

    suspend fun getProductByPId(pId:Int):Product
    suspend fun insertProduct(product: Product)
    suspend fun deleteProduct(product: Product)
    //////////////////////////////////
    fun getComments():Flow<List<Comment>>
    suspend fun postComment(TextNazar_:String,NumRank:String):Call<String?>


}