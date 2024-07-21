package com.pro_1.prm_01_2

data class Product(var name: String, var expirationDate: String, var category: Category, var quantity: Int = 0)

enum class Category {
    SPOZYWCZE,
    LEKI,
    KOSMETYKI
}