package com.example.composeapp.domain.use_cases

import com.example.composeapp.data.ProductService
import com.example.composeapp.domain.ApiResult
import com.example.composeapp.domain.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetProductsById(
    private val productService: ProductService
) {
    operator fun invoke(id: Int): Flow<ApiResult<Product>> = flow {
        try {
            emit(ApiResult.Loading("Cargando"))
            val response = productService.getProductById(id)
            emit(ApiResult.Success(data = response))
        } catch (e: Exception) {
            emit(ApiResult.Error(message = "Error", data = null))
        }
    }
}