package com.dev.bayan_ibrahim.compose_course_2.domain

import androidx.annotation.StringRes
import com.dev.bayan_ibrahim.compose_course_2.R


enum class OrderLunchTrayScreens (@StringRes val id: Int){
    START (R.string.app_name),
    ENTREE (R.string.entree),
    SIDE_DISH (R.string.side_dish),
    ACCOMPANIMENT (R.string.accopaniment),
    SUMMARY (R.string.summary)
}