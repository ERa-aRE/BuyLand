package com.example.buyland.domain.use_cases

import com.example.buyland.domain.model.User
import com.example.buyland.domain.repository.BuylandRepository

class InsertUser(
    private val repository: BuylandRepository
) {
    suspend operator fun invoke(user: User){
        repository.insertUser(user)
    }
}