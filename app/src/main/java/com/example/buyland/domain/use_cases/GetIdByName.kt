package com.example.buyland.domain.use_cases

import com.example.buyland.domain.repository.BuylandRepository

class GetIdByName(
    private val repository: BuylandRepository
) {
    suspend operator fun invoke(name:String):Int? {
        return repository.getIdByName(name)
    }
}