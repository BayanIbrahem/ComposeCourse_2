package com.dev.bayan_ibrahim.compose_course_2.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.text.NumberFormat

sealed class Place(
    open val name: String,
    @DrawableRes open val imageRes: Int,
    open val rate: Float,
    @StringRes open val description: Int,
    open val location: String,
    open val unitPriceAverage: Float,
    open val unitPriceAverageDescription: String,
){
    data class Cafe(
        override val name: String,
        @DrawableRes override val imageRes: Int,
        override val rate: Float,
        @StringRes override val description: Int,
        override val location: String,
        override val unitPriceAverage: Float,
        override val unitPriceAverageDescription: String,
    ): Place(name, imageRes, rate, description, location, unitPriceAverage, unitPriceAverageDescription)

    data class Restaurant(
        override val name: String,
        @DrawableRes override val imageRes: Int,
        override val rate: Float,
        @StringRes override val description: Int,
        override val location: String,
        override val unitPriceAverage: Float,
        override val unitPriceAverageDescription: String,
    ): Place(name, imageRes, rate, description, location, unitPriceAverage, unitPriceAverageDescription)

    data class Hotel(
        override val name: String,
        @DrawableRes override val imageRes: Int,
        override val rate: Float,
        @StringRes override val description: Int,
        override val location: String,
        override val unitPriceAverage: Float,
        override val unitPriceAverageDescription: String,
    ): Place(name, imageRes, rate, description, location, unitPriceAverage, unitPriceAverageDescription)
    val formattedPrice: String
        get() {
            return NumberFormat.getCurrencyInstance().format(unitPriceAverage)
        }
    val formattedPriceWithUnit: String
        get() {
            return "$formattedPrice per $unitPriceAverageDescription"
        }
}
