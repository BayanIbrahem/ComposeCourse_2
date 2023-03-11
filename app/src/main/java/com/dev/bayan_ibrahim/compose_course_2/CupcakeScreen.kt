package com.dev.bayan_ibrahim.compose_course_2

import android.content.Intent
import android.content.Intent.ACTION_SEND
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dev.bayan_ibrahim.compose_course_2.data.DataSource.flavors
import com.dev.bayan_ibrahim.compose_course_2.domain.CupcakeScreens
import com.dev.bayan_ibrahim.compose_course_2.ui.SelectOptionsScreen
import com.dev.bayan_ibrahim.compose_course_2.ui.StartOrderScreen
import com.dev.bayan_ibrahim.compose_course_2.ui.SummaryScreen
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme
import com.dev.bayan_ibrahim.compose_course_2.ui.view_model.OrderViewModel

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@Composable
fun CupcakeAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    currentScreen: CupcakeScreens,
) {
    TopAppBar(
        title = { Text(stringResource(id = currentScreen.id)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun CupcakeApp(modifier: Modifier = Modifier, viewModel: OrderViewModel = viewModel()){
    // TODO: Create NavController

    // TODO: Get current back stack entry

    // TODO: Get the name of the current screen

    val controller = rememberNavController()
    val backStackEntry by controller.currentBackStackEntryAsState()
    val currentScreen = CupcakeScreens.valueOf(
        backStackEntry?.destination?.route ?: CupcakeScreens.Start.name
    )
    val canNavBack = controller.previousBackStackEntry != null

    Scaffold(
        topBar = {
            CupcakeAppBar(
                canNavigateBack = canNavBack,
                navigateUp = { controller.navigateUp() },
                currentScreen = currentScreen,
            )
        }
    ) {
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = controller,
            startDestination = CupcakeScreens.Start.name,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            composable(route = CupcakeScreens.Start.name) {
                StartOrderScreen(
                    orderQuantities = listOf(1, 6, 12),
                    selectQuantity = {
                            quantity -> viewModel.setQuantity(quantity)
                            controller.navigate(CupcakeScreens.Flavor.name)
                    }
                )
            }
            composable(route = CupcakeScreens.Flavor.name) {
                SelectOptionsScreen(
                    options = flavors.map {
                            id ->
                        stringResource(id = id)
                    },
                    selected = uiState.flavor,
                    onSelectedChange = {flavor ->
                        viewModel.setFlavor(flavor)
                    },
                    price = uiState.price,
                    onCancel = {
                        cancelAndPopNavigationStack(viewModel, controller)
                    },
                    onNext = {
                        controller.navigate(CupcakeScreens.Date.name)
                    },
                )
            }
            composable(route = CupcakeScreens.Date.name) {
                SelectOptionsScreen(
                    options = uiState.pickupOptions,
                    selected = uiState.date,
                    onSelectedChange = { date ->
                        viewModel.setDate(date)
                    },
                    price = uiState.price,
                    onCancel = {
                        cancelAndPopNavigationStack(viewModel, controller)
                    },
                    onNext = {
                        controller.navigate(CupcakeScreens.Summary.name)
                    },
                )
            }
            composable(route = CupcakeScreens.Summary.name) {
                val context = LocalContext.current
                SummaryScreen(
                    orderUiState = uiState,
                    onCancel = {
                        cancelAndPopNavigationStack(viewModel, controller)
                    },
                    onSendToAnotherApp = { summary ->
                        val intent = Intent(ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra( context.getString(R.string.order_summary) , summary)
                        }
                        context.startActivity(
                            Intent.createChooser(
                                intent,
                                context.getString(R.string.new_cupcake_order)
                            )
                        )

                    }
                )
            }
        }
    }
}

private fun cancelAndPopNavigationStack(viewModel: OrderViewModel, controller: NavHostController) {
    viewModel.resetOrder()
    controller.popBackStack(route = CupcakeScreens.Start.name, inclusive = false)
}
@Preview(showBackground = true)
@Composable
fun CupCakeAppLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            CupcakeApp()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CupCakeAppDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            CupcakeApp()
        }
    }
}
