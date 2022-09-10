package com.example.buyland.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    val pName : String,
    val pPrice : String,
    val pDescription : String,
    val userId :Int ,
    @PrimaryKey val pId :Int? = null
)
