package com.example.buyland.domain.api_use_cases

import com.example.buyland.data.remote.BuyLandApi
import com.example.buyland.domain.model.Comment
import com.example.buyland.domain.repository.BuylandRepository
import kotlinx.coroutines.flow.Flow

class GetCommentUseCase(
    private val repository : BuylandRepository ,
) {
    operator fun invoke():Flow<List<Comment>>{
        return repository.getComments()
    }
}