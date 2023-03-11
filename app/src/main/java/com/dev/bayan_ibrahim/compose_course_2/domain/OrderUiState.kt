package com.dev.bayan_ibrahim.compose_course_2.domain

data class OrderUiState(
    val quantity: Int = 0, /* 1, 6, 12 */
    val flavor: String = "",
    val date: String = "",
    val price: Float = 0f,
    val pickupOptions: List<String> = listOf(),
) {
}
