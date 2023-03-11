package com.dev.bayan_ibrahim.compose_course_2.ui

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan_ibrahim.compose_course_2.R
import com.dev.bayan_ibrahim.compose_course_2.domain.OrderUiState
import com.dev.bayan_ibrahim.compose_course_2.ui.components.FormattedPrice
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme

@Composable
fun SummaryScreen(
    modifier: Modifier = Modifier,
    orderUiState: OrderUiState,
    onCancel: () -> Unit,
    onSendToAnotherApp: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        TotalSummaryItems(
            modifier = Modifier.padding(bottom = 16.dp),
            orderUiState = orderUiState,
        )
        FormattedPrice(modifier = Modifier.align(Alignment.End), price = orderUiState.price)
        SummaryButtons (onCancel = { onCancel() }, onSendToAnotherApp = { onSendToAnotherApp(orderUiState.toString()) })
    }
}

@Composable
private fun TotalSummaryItems(modifier: Modifier = Modifier, orderUiState: OrderUiState) {
    Column (modifier = modifier) {
        SingleSummaryItem(
            title = stringResource(id = R.string.quantity),
            value = orderUiState.quantity.toString(),
        )
        SingleSummaryItem(
            title = stringResource(id = R.string.flavor),
            value = orderUiState.flavor,
        )
        SingleSummaryItem(
            title = stringResource(id = R.string.pickup_date),
            value = orderUiState.date,
        )
    }
}
@Composable
private fun SingleSummaryItem(modifier: Modifier = Modifier, title: String, value: String) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(modifier = Modifier.padding(bottom = 8.dp), text = title.uppercase())
        Text(text = value, fontWeight = FontWeight.Bold)
        Divider()
    }
}

@Composable
private fun SummaryButtons(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit,
    onSendToAnotherApp: () -> Unit
) {
    Column (
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onSendToAnotherApp() },
        ) {
            Text(text = stringResource(id = R.string.next))
        }
        Spacer(modifier = Modifier.padding(4.dp))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onCancel() },
        ) {
            Text(text = stringResource(id = R.string.cancel))
        }
    }
}

@Preview (showBackground = true)
@Composable
private fun SingleSummaryItemPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(color = MaterialTheme.colors.surface) {
            SingleSummaryItem(title = "title", value = "value")
        }
    }
}

@Preview (showBackground = true)
@Composable
private fun SingleSummaryItemPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(color = MaterialTheme.colors.surface) {
            SingleSummaryItem(title = "title", value = "value")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SummaryScreenPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            SummaryScreen(orderUiState = OrderUiState(), onCancel = {}, onSendToAnotherApp = {})
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun SummaryScreenPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            SummaryScreen(orderUiState = OrderUiState(), onCancel = {}, onSendToAnotherApp = {})
        }
    }
}
