package com.example.deber03_paulroman

import androidx.annotation.DrawableRes

data class Suggestion(
    val title: String,
    @DrawableRes val imageResId: Int
)
