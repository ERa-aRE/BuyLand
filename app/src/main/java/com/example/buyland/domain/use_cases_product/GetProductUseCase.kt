package com.example.buyland.domain.use_cases_product

import com.example.buyland.domain.model.Product
import com.example.buyland.domain.repository.BuylandRepository

class GetProductUseCase(
    private val repository: BuylandRepository
) {
    suspend operator fun invoke(pId:Int):Product{
        return repository.getProductByPId(pId)

    }
}