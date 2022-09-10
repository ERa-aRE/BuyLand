package com.example.buyland.data.repository

import android.util.Log
import com.example.buyland.data.local.BuyLandPDao
import com.example.buyland.data.local.BuylandDao
import com.example.buyland.data.remote.BuyLandApi
import com.example.buyland.domain.model.*
import com.example.buyland.domain.repository.BuylandRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.HttpException
import java.io.IOException


class BuylandRepositoryImpl(
    private val dao:BuylandDao,
    private val pDao:BuyLandPDao,
    private val api : BuyLandApi
):BuylandRepository {
    override suspend fun getIdByName(userName: String): Int? {
        return dao.getIdByName(userName)
    }

    override suspend fun checkUserName(userName: String): Boolean {
        return dao.checkUserName(userName)
    }

    override suspend fun getUserById(id: Int): User {
        return dao.getUserById(id)
    }
    override suspend fun getPasswordById(id:Int):String{
        return dao.getPasswordById(id)
    }

    override suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    override suspend fun updateUser(user: User) {
        dao.updateUser(user)
    }

    ///////////////////
    override fun getProducts(userId: Int): Flow<List<Product>> {
        return pDao.getProducts(userId=userId)
    }

    override suspend fun getProductByPId(pId: Int): Product {
        return pDao.getProductByPId(pId=pId)
    }

    override suspend fun insertProduct(product: Product) {
        pDao.insertProduct(product=product)
    }

    override suspend fun deleteProduct(product: Product) {
        pDao.deleteProduct(product=product)
    }

    override fun getComments(): Flow<List<Comment>> = flow{

        try{
            val remoteComment = api.getComments().Comments
            remoteComment?.let{
                emit(remoteComment.map{it.toComment()})
            }

        }catch (e: HttpException){
            Log.e("Error","${e.message})")


        }catch (e: IOException){
            Log.e("Error","${e.message})")


        }



    }
    ////////
    override suspend fun postComment(TextNazar_:String,NumRank:String): Call<String?> {
        return api.postComment(TextNazar_,NumRank)
    }

}