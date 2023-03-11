package com.dev.bayan_ibrahim.compose_course_2.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan_ibrahim.compose_course_2.R
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme

@Composable
fun StartOrderScreen(
    modifier: Modifier = Modifier,
    orderQuantities: List<Int> = listOf(1, 6, 12),
    selectQuantity: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Spacer(modifier = Modifier.padding(16.dp))
        MainImage()
        Spacer(modifier = Modifier.padding(16.dp))
        MainText()
        Spacer(modifier = Modifier.padding(16.dp))
        for (q in orderQuantities) {
            QuantitySelectButton(
                numberOfCupcakes = q,
                onClick = { selectQuantity(q) },
            )
        }
    }
}

@Composable
private fun MainImage(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .size(300.dp),
        painter = painterResource(id = R.drawable.cupcake),
        contentDescription = stringResource(R.string.cupcake_image_description),
    )
}
@Composable
private fun MainText(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.order_cupcakes),
        style = MaterialTheme.typography.h5,
    )
}
@Composable
private fun QuantitySelectButton(modifier: Modifier = Modifier, numberOfCupcakes: Int, onClick: () -> Unit) {
    val text = getQuantityButtonText(numberOfCupcakes)
    Button(
        modifier = modifier
            .widthIn(min = 250.dp),
    onClick = onClick
    ) {
        Text(
            modifier = modifier,
            text = text,
            style = MaterialTheme.typography.button,
        )
    }
}

private fun getQuantityButtonText(cupcakes: Int): String {
    return when(cupcakes) {
        1 -> "One Cupcake"
        6 -> "Six Cupcakes"
        12 -> "Twelve Cupcakes"
        else -> "$cupcakes cupcakes"
    }
}

@Preview (showBackground = true)
@Composable
private fun StartOrderScreenPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            StartOrderScreen(selectQuantity = {  })
        }
    }
}


@Preview (showBackground = true)
@Composable
private fun StartOrderScreenPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            StartOrderScreen(selectQuantity = {  })
        }
    }
}
