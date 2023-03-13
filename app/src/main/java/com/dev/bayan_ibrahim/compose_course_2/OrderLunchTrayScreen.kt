package com.dev.bayan_ibrahim.compose_course_2


import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dev.bayan_ibrahim.compose_course_2.domain.OrderLunchTrayScreens
import com.dev.bayan_ibrahim.compose_course_2.ui.OrderChooseScreen
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme
import com.dev.bayan_ibrahim.compose_course_2.ui.view_model.OrderViewModel
import com.dev.bayan_ibrahim.compose_course_2.data.DataSource
import com.dev.bayan_ibrahim.compose_course_2.domain.MenuItem
import com.dev.bayan_ibrahim.compose_course_2.ui.OrderCheckoutScreen
import com.dev.bayan_ibrahim.compose_course_2.ui.StartOrderScreen

@Composable
fun OrderLunchTrayApp(
    modifier: Modifier = Modifier,
    controller: NavHostController = rememberNavController(),
    orderViewModel: OrderViewModel = viewModel(),
) {
    val orderUiState by orderViewModel.orderUiState.collectAsState()
    val backStack by controller.currentBackStackEntryAsState()
    val currentScreen = OrderLunchTrayScreens.valueOf(
        backStack?.destination?.route ?: OrderLunchTrayScreens.START.name
    )
    Scaffold(
        modifier = modifier,
        topBar = {
            LunchTrayTopAppBar(
                currentScreen = currentScreen,
                navigationUp = {
                    controller.navigateUp()
                }
            )
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = controller,
            startDestination = OrderLunchTrayScreens.START.name,
        ) {
            composable(route = OrderLunchTrayScreens.START.name) {
                StartOrderScreen {
                    controller.navigate(OrderLunchTrayScreens.ENTREE.name)
                }

            }
            composable(route = OrderLunchTrayScreens.ENTREE.name) {
                OrderChooseScreen(
                    items = DataSource.entreeMenuItems,
                    selectedItem = orderUiState.entree ?: MenuItem.EntreeItem(),
                    onSelectItem = { item ->
                        orderViewModel.setEntree(item as MenuItem.EntreeItem)
                    },
                    onCancel = {
                        reset(orderViewModel, controller)
                    },
                    onSubmit = {
                        controller.navigate(OrderLunchTrayScreens.SIDE_DISH.name)
                    }
                )
            }
            composable(route = OrderLunchTrayScreens.SIDE_DISH.name) {
                OrderChooseScreen(
                    items = DataSource.sideDishMenuItems,
                    selectedItem = orderUiState.sideDish ?: MenuItem.SideDishItem(),
                    onSelectItem = { item ->
                        orderViewModel.setSideDish(item as MenuItem.SideDishItem)
                    },
                    onCancel = {
                        reset(orderViewModel, controller)
                    },
                    onSubmit = {
                        controller.navigate(OrderLunchTrayScreens.ACCOMPANIMENT.name)
                    }
                )
            }
            composable(route = OrderLunchTrayScreens.ACCOMPANIMENT.name) {
                OrderChooseScreen(
                    items = DataSource.accompanimentMenuItems,
                    selectedItem = orderUiState.accompaniment ?: MenuItem.AccompanimentItem(),
                    onSelectItem = { item ->
                        orderViewModel.setAccompaniment(item as MenuItem.AccompanimentItem)
                    },
                    onCancel = {
                        reset(orderViewModel, controller)
                    },
                    onSubmit = {
                        controller.navigate(OrderLunchTrayScreens.SUMMARY.name)
                    }
                )
            }
            composable(route = OrderLunchTrayScreens.SUMMARY.name) {
                OrderCheckoutScreen(
                    orderUiState = orderUiState,
                    onCancel = {
                        reset(orderViewModel, controller)
                    },
                    onSubmit = {
                        reset(orderViewModel, controller)
                    },
                )
            }
        }
    }

}

private fun reset(viewModel: OrderViewModel, controller: NavHostController) {
    viewModel.resetOrder()
    controller.popBackStack(OrderLunchTrayScreens.START.name, false)
}
/*
fun TopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = AppBarDefaults.TopAppBarElevation ){}

* */
@Composable
private fun LunchTrayTopAppBar(
    modifier: Modifier = Modifier,
    currentScreen: OrderLunchTrayScreens,
    navigationUp: () -> Unit,
) {
    val title = stringResource(id = currentScreen.id)
    TopAppBar(
        modifier = modifier,
        title = { Text(text = title) },
        navigationIcon = {
            if (currentScreen.name != OrderLunchTrayScreens.START.name) {
                IconButton(onClick = navigationUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Navigate Up",
                    )
                }
            }
        }
    )
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun OrderLunchTrayAppFunctionForPreview() {
    OrderLunchTrayApp()
}

@Preview(showBackground = false)
@Composable
private fun OrderLunchTrayAppPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            OrderLunchTrayAppFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun OrderLunchTrayAppPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            OrderLunchTrayAppFunctionForPreview()
        }
    }
}
