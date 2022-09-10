package com.example.buyland.domain.use_cases_product

import com.example.buyland.domain.model.Product
import com.example.buyland.domain.repository.BuylandRepository
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(private val repository:BuylandRepository) {

    operator fun invoke(userId:Int):Flow<List<Product>>{
        return repository.getProducts(userId=userId)
    }
}