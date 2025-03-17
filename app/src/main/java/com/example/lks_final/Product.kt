package com.example.lks_final

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val name: String,
    val price: Int,
    val image: Int
) : Parcelable

