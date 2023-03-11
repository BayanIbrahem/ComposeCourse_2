package com.dev.bayan_ibrahim.compose_course_2.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan_ibrahim.compose_course_2.R
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme
import java.text.NumberFormat

@Composable
fun FormattedPrice(modifier: Modifier = Modifier, price: Float) {
    val formattedPrice = NumberFormat.getCurrencyInstance().format(price)
    Text(
        modifier = modifier
            .padding(16.dp),
        text = stringResource(id = R.string.price, formattedPrice),
    )
}

@Preview(showBackground = true)
@Composable
fun FormattedPricePreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        FormattedPrice(price = 9.99f)
    }
}

@Preview(showBackground = true)
@Composable
fun FormattedPricePreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        FormattedPrice(price = 9.99f)
    }
}
