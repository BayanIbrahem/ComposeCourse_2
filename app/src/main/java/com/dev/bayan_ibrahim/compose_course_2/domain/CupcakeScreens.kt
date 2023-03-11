package com.dev.bayan_ibrahim.compose_course_2.domain

import androidx.annotation.StringRes
import com.dev.bayan_ibrahim.compose_course_2.R

enum class CupcakeScreens(@StringRes val id: Int) {
    Start(R.string.app_name),
    Flavor(R.string.flavor),
    Date(R.string.pickup_date),
    Summary(R.string.order_summary),
}