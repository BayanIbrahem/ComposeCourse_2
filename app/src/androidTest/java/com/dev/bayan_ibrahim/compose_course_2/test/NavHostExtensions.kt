package com.dev.bayan_ibrahim.compose_course_2.test

import androidx.navigation.NavHostController
import org.junit.Assert.assertEquals

fun NavHostController.assertCurrentRouteName(expectedName: String) {
    assertEquals(expectedName, this.currentDestination?.route)
}