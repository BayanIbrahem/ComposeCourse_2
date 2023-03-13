package com.dev.bayan_ibrahim.compose_course_2.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dev.bayan_ibrahim.compose_course_2.R
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme

@Composable
fun StartOrderScreen(
    modifier: Modifier = Modifier,
    onClickStartButton: () -> Unit,
) {
    Box (
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ){
        StartOrderButton {
            onClickStartButton()
        }
    }
}

@Composable
private fun StartOrderButton (
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
    ) {
        Text(text = stringResource(id = R.string.start_order_button))
    }
}
@Preview(showBackground = false)
@Composable
private fun StartOrderScreenPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            StartOrderScreen{}
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun StartOrderScreenPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            StartOrderScreen{}
        }
    }
}
