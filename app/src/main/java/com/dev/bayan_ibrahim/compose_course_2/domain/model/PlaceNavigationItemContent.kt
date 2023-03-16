package com.dev.bayan_ibrahim.compose_course_2.domain.model

import androidx.annotation.DrawableRes

data class PlaceNavigationItemContent(
    val type: PlacesTypes,
    @DrawableRes val icon: Int,
    val text: String,
)
