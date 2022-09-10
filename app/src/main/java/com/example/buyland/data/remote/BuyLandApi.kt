package com.example.buyland.data.remote

import com.example.buyland.data.remote.dto.CommentDto
import com.example.buyland.domain.model.NetResponse
import com.example.buyland.domain.model.PostComment
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface BuyLandApi {
    @GET("GetListTNazarat/***********,3")
    suspend fun getComments():CommentDto

//{Unique_code},{UsersId},{ServiserId},{TextNazar_},{NumRank},{OrderId}

    @POST("AddNazarForServiser/************,1,3,{TextNazar_},{NumRank},30098")
    fun postComment(
      @Path("TextNazar_")TextNazar_:String ,@Path("NumRank")NumRank :String
    ): Call<String?>


    






    companion object {
        const val BASE_URL = "http://onlinurlspelatform.setapi.ir/api/G1/"


    }
}
