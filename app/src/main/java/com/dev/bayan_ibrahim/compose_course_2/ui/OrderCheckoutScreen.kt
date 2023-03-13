package com.dev.bayan_ibrahim.compose_course_2.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan_ibrahim.compose_course_2.R
import com.dev.bayan_ibrahim.compose_course_2.domain.MenuItem
import com.dev.bayan_ibrahim.compose_course_2.domain.OrderUiState
import com.dev.bayan_ibrahim.compose_course_2.ui.components.ChoosingButtonsRow
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme

@Composable
fun OrderCheckoutScreen(
    modifier: Modifier = Modifier,
    orderUiState: OrderUiState,
    onCancel: () -> Unit,
    onSubmit: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OrderSummary(orderUiState = orderUiState)
        PriceSummary(orderUiState = orderUiState)
        Spacer(modifier = Modifier.padding(4.dp))
        ChoosingButtonsRow(
            cancelText = R.string.cancel,
            submitText = R.string.submit,
            onCancel = onCancel,
            onSubmit = onSubmit,
            submitAvailable = true
        )
    }
}

@Composable
private fun OrderSummary(
    modifier: Modifier = Modifier,
    orderUiState: OrderUiState,
) {
    val entree = orderUiState.entree ?: MenuItem.EntreeItem("no name", "no description", -1.0)
    val sideDish = orderUiState.sideDish ?: MenuItem.EntreeItem("no name", "no description", -1.0)
    val accompaniment = orderUiState.accompaniment ?: MenuItem.EntreeItem("no name", "no description", -1.0)
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.order_summary),
            fontWeight = FontWeight.Bold,
        )
        OrderSummaryLines(orderUiState = orderUiState)
        Divider()
    }
}

@Composable
private fun OrderSummaryLines(
    modifier: Modifier = Modifier,
    orderUiState: OrderUiState,
) {
    val entree = orderUiState.entree ?: MenuItem.EntreeItem("no name", "no description", -1.0)
    val sideDish = orderUiState.sideDish ?: MenuItem.EntreeItem("no name", "no description", -1.0)
    val accompaniment = orderUiState.accompaniment ?: MenuItem.EntreeItem("no name", "no description", -1.0)
    Column(
        modifier = modifier,
    ) {
        SingleOrderSummaryLine(
            text = entree.name,
            formattedPrice = entree.getFormattedPrice(),
        )
        SingleOrderSummaryLine(
            text = sideDish.name,
            formattedPrice = sideDish.getFormattedPrice()
        )
        SingleOrderSummaryLine(
            text = accompaniment.name,
            formattedPrice = accompaniment.getFormattedPrice()
        )
    }
}
@Composable
private fun SingleOrderSummaryLine(
    modifier: Modifier = Modifier,
    text: String,
    formattedPrice: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(modifier = Modifier.weight(1f), text = text)
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = formattedPrice)
    }
}
@Composable
private fun PriceSummary(
    modifier: Modifier = Modifier,
    orderUiState: OrderUiState,
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        // TODO make code cleaner by adding %s parameter ot string resources and pass price to it
        PriceSummaryLine(
            text = stringResource(R.string.total_items_price),
            formattedPrice = orderUiState.getItemTotalPriceFormatted(),
            isBold = false,
        )
        PriceSummaryLine(
            text = stringResource(R.string.tax),
            formattedPrice = orderUiState.getTaxFormatted(),
            isBold = false,
        )
        PriceSummaryLine(
            text = stringResource(R.string.total_order_price),
            formattedPrice = orderUiState.getOrderTotalPriceFormatted(),
            isBold = true,
        )
    }
}

@Composable
private fun PriceSummaryLine(
    modifier: Modifier = Modifier,
    text: String,
    formattedPrice: String,
    isBold: Boolean
) {

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        val fontWeight = if(isBold) FontWeight.Bold else FontWeight.Normal
        Text(text = text, fontWeight = fontWeight)
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = formattedPrice, fontWeight = fontWeight)
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun OrderCheckoutScreenFunctionForPreview() {
    OrderCheckoutScreen(
        orderUiState = OrderUiState(),
        onCancel = {},
        onSubmit = {},
    )
}

@Preview(showBackground = false)
@Composable
private fun OrderCheckoutScreenPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            OrderCheckoutScreenFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun OrderCheckoutScreenPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            OrderCheckoutScreenFunctionForPreview()
        }
    }
}
