package com.example.composeapp.presentation.states

import com.example.composeapp.domain.models.Product

data class ProductsState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val errorMessage: String = ""
)