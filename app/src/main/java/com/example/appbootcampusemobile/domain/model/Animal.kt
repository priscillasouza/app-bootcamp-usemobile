package com.example.appbootcampusemobile.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Animal(
    val id: String,
    val name: String? = "",
    val image: String? = "",
    val description: String? = "",
    val species: String? = "",
    val age: Int? = 0,
    var favorite: Boolean = false
):Parcelable