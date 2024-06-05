package com.example.storeapp.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.domain.ApiResult
import com.example.composeapp.domain.use_cases.GetProducts
import com.example.composeapp.presentation.states.ProductsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProducts
): ViewModel() {
    private var productsJob: Job? = null
    private var _productsState = mutableStateOf(ProductsState())
    val productsState = _productsState

    init {
        //Traeme los productos
        getProducts()
    }

    private fun getProducts() {
        productsJob?.cancel()
        productsJob = getProductsUseCase().onEach { result ->
            when(result) {
                is ApiResult.Loading -> {
                    //Decirle al usuario que esta cargando la aplicación
                    _productsState.value = ProductsState(isLoading = true)
                }

                is ApiResult.Error -> {
                    //Decirle al usuario que hubo un error
                    _productsState.value = ProductsState(errorMessage = result.message)
                }

                is ApiResult.Success -> {
                    //Mostrar la lista
                    _productsState.value = ProductsState(products = result.data!!)
                }
            }
        }.launchIn(viewModelScope)
    }
}