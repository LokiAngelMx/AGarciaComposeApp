package com.example.composeapp.domain.models

data class Product(
    val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
){
    val computedPrice get() = "$$price"
    val computedTitle get() = if(title.length > 11) title.substring(0,11) else title
    companion object{
        val productList = List(10){
            Product(
                id = 1,
                category = "Electronics",
                description = "Sistema avanzado de dos cámaras de 12 MP (ultra gran angular y gran angular), modo Noche, Deep Fusion, HDR Inteligente 3 y grabación de video 4K HDR en Dolby Vision.",
                image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                price = 1000.0,
                rating = Rating(4, 4.5),
                title = "iPhone 12 Pro Max"
            )
        }
    }
}