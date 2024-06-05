package com.example.composeapp.domain.use_cases

import com.example.composeapp.data.ProductService
import com.example.composeapp.domain.ApiResult
import com.example.composeapp.domain.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetProducts(
    private val productService: ProductService
) {
    operator fun invoke(): Flow<ApiResult<List<Product>>> = flow {
        try {
            emit(ApiResult.Loading("Cargando"))
            val response = productService.getProducts()
            emit(ApiResult.Success(response))
        } catch (e: Exception) {
            emit(ApiResult.Error(message = "La petición falló", data = emptyList()))
        }
    }
}