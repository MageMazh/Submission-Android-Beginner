package com.dicoding.myrecyclerview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val name: String,
    val description: String,
    val photo: Int,
    val year: String,
    val score: String,
    val duration: String,
    val director: String,
    val producer: String,
    val wiki: String
) : Parcelable