package com.example.catlovers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    val type: String,
    val description: String,
    val photo: Int,
    val nameOther: String,
    val characteristics: String
):Parcelable