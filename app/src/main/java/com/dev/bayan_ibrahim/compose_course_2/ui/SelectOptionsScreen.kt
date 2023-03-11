package com.dev.bayan_ibrahim.compose_course_2.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan_ibrahim.compose_course_2.R
import com.dev.bayan_ibrahim.compose_course_2.ui.components.FormattedPrice
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme

@Composable
fun SelectOptionsScreen(
    modifier: Modifier = Modifier,
    options: List<String> ,
    selected: String,
    onSelectedChange: (String) -> Unit,
    price: Float,
    onCancel: () -> Unit,
    onNext: () -> Unit,
) {
    Column(modifier = modifier) {
        for (option in options) {
            SingleOption(
                optionText = option,
                selected = selected == option,
                onSelect = {
                    onSelectedChange(option)
                }
            )
        }
        Divider(modifier = Modifier.padding(bottom = 16.dp))
        FormattedPrice(modifier = Modifier.align(Alignment.End), price = price)
        Buttons(onCancel = { onCancel() }, onNext = { onNext() })
    }
}

@Composable
private fun SingleOption(
    modifier: Modifier = Modifier,
    optionText: String,
    selected: Boolean,
    onSelect: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .selectable(selected = false, onClick = { onSelect() })
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = optionText,
        )
    }
}

@Composable
private fun Buttons(modifier: Modifier = Modifier, onCancel: () -> Unit, onNext: () -> Unit) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
    ) {
        OutlinedButton(
            modifier = Modifier.weight(1f),
            onClick = { onCancel() },
        ) {
            Text(text = stringResource(id = R.string.cancel))
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            modifier = Modifier.weight(1f),
            onClick = { onNext() },
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}
@Preview
@Composable
private fun SingleOptionPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(color = MaterialTheme.colors.surface) {
            SingleOption(optionText = "option text", selected = false) {

            }
        }
    }
}

@Preview
@Composable
private fun SingleOptionPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(color = MaterialTheme.colors.surface) {
            SingleOption(optionText = "option text", selected = false) {

            }
        }
    }
}
@Preview (showBackground = true)
@Composable
private fun SelectOptionsScreenPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            SelectOptionsScreen(
                options = listOf("test option #1", "test option #2", "test option #3"),
                selected = "",
                onSelectedChange = {},
                price = 0f,
                onCancel = {},
                onNext = {},
            )
        }
    }
}
@Preview (showBackground = true)
@Composable
private fun SelectOptionsScreenPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            SelectOptionsScreen(
                options = listOf("test option #1", "test option #2", "test option #3"),
                selected = "",
                price = 0f,
                onSelectedChange = {},
                onCancel = {},
                onNext = {},
            )
        }
    }
}
