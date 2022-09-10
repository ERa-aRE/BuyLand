package com.example.buyland.domain.use_cases

import com.example.buyland.domain.model.User
import com.example.buyland.domain.repository.BuylandRepository

class GetUserById (
    private val repository : BuylandRepository
        ){
    suspend operator fun invoke(id: Int):User{
       return repository.getUserById(id)
    }
}