package com.example.buyland.domain.use_cases

import com.example.buyland.domain.repository.BuylandRepository

class CheckUserName (
    private val repository: BuylandRepository
        ){
    suspend operator fun invoke(name:String):Boolean{
       return repository.checkUserName(name)
    }
}