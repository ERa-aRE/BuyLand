package com.example.buyland.domain.api_use_cases

import com.example.buyland.domain.model.NetResponse
import com.example.buyland.domain.model.PostComment
import com.example.buyland.domain.repository.BuylandRepository
import retrofit2.Call
import retrofit2.Response

class PostCommentUseCase(
    private val repository : BuylandRepository,
) {
    suspend operator fun invoke(TextNazar_:String,NumRank:String): Call<String?> {
        return repository.postComment(TextNazar_,NumRank)
    }
}