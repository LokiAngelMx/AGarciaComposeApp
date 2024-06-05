package com.example.storeapp.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.domain.ApiResult
import com.example.composeapp.domain.use_cases.GetProductsById
import com.example.composeapp.presentation.states.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor (
    savedStateHandle: SavedStateHandle,
    private val getProductUseCase: GetProductsById
): ViewModel() {
    private val productId = savedStateHandle.get<Int>("productId") ?: 0
    private var productsJob: Job? = null
    private var _productState = mutableStateOf(ProductState())
    val productState = _productState

    init {
        getProduct()
    }

    private fun getProduct() {
        productsJob?.cancel()
        productsJob = getProductUseCase(productId).onEach { result ->
            when(result) {
                is ApiResult.Loading -> {
                    _productState.value = ProductState(isLoading = true)
                }

                is ApiResult.Success -> {
                    _productState.value = ProductState(product = result.data!!, isLoading = false)
                }

                is ApiResult.Error -> {
                    _productState.value = ProductState(errorMessage = result.message, isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}