package com.dev.bayan_ibrahim.compose_course_2.data

import com.dev.bayan_ibrahim.compose_course_2.R

/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
const val PRICE_PER_CUPCAKE = 1.1f
const val TIP_FOR_SAME_DAY_PICKING_UP = 0.1f
const val PRICE_FOR_SAME_DAY_PICKUP = PRICE_PER_CUPCAKE * (1 + TIP_FOR_SAME_DAY_PICKING_UP)
object DataSource {
    val flavors = listOf(
        R.string.vanilla,
        R.string.chocolate,
        R.string.red_velvet,
        R.string.salted_caramel,
        R.string.coffee
    )
}