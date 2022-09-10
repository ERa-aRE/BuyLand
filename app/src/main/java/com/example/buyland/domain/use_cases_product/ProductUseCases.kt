package com.example.buyland.domain.use_cases_product

data class ProductUseCases(
    val getProductsUseCase: GetProductsUseCase,
    val getProductUseCase: GetProductUseCase,
    val insertProduct: InsertProduct,
    val deleteProductUseCase: DeleteProductUseCase,
)
