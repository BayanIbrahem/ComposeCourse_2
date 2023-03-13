package com.dev.bayan_ibrahim.compose_course_2.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.dev.bayan_ibrahim.compose_course_2.CupcakeApp
import com.dev.bayan_ibrahim.compose_course_2.domain.CupcakeScreens
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CupcakeScreenNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController =
                TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
            CupcakeApp(controller = navController)
        }
    }
    @Test
    fun cupcakeNavHost_TestStartDestination(){
        navController.assertCurrentRouteName(CupcakeScreens.Start.name)
//        composeTestRule.onNodeWithStringId(R.string.) to verify that the screen has nodes.
    }
    
    @Test
    fun cupcakeNavTest_StartDestination_HasNoUpButton () {
        // add description to navigation button and test if there an element has this description.
//        val backText = composeTestRule.activity.getString(R.string.back_button)
//        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }
    @Test
    fun cupcakeNavHost_clickOneCupcake_navigatesToSelectFlavorScreen() {
        // my code doesn't has this way implementing elements so i can't use it

//        composeTestRule.onNodeWithStringId(R.string.one_cupcake)
//            .performClick()
//        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    @Test
    fun selectOptionScreen_verifyContent() {
//        // Given list of options
//        val flavours = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
//        // And sub total
//        val subTotal = "$100"
//
//        // When SelectOptionScreen is loaded
//        composeTestRule.setContent {
//            CupcakeTheme {
//                SelectOptionScreen(subtotal = subTotal, options = flavours)
//            }
//        }
//
//        // Then all the options are displayed on the screen.
//        flavours.forEach { flavour ->
//            composeTestRule.onNodeWithText(flavour).assertIsDisplayed()
//        }
//        // And then the subtotal is displayed correctly.
//        composeTestRule.onNodeWithText(
//            composeTestRule.activity.getString(
//                R.string.subtotal_price,
//                subTotal
//            )
//        ).assertIsDisplayed()
//        // And then the next button is disabled
//        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }
}