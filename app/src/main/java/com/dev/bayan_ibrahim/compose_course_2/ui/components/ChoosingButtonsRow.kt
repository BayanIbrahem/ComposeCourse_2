package com.dev.bayan_ibrahim.compose_course_2.ui.components


import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme
import com.dev.bayan_ibrahim.compose_course_2.R

@Composable
fun ChoosingButtonsRow(
    modifier: Modifier = Modifier,
    @StringRes cancelText: Int,
    @StringRes submitText: Int,
    onCancel: () -> Unit,
    onSubmit: () -> Unit,
    submitAvailable: Boolean,
) {
    Row (
        modifier = modifier,
    ) {
        OutlinedButton(
            modifier = Modifier.weight(1f),
            onClick = onCancel,
        ) {
            Text(text = stringResource(id = cancelText))
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            modifier = Modifier.weight(1f),
            onClick = onSubmit,
            enabled = submitAvailable,
        ) {
            Text(text = stringResource(id = submitText))
        }
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun ChoosingButtonsRowFunctionForPreview() {
    ChoosingButtonsRow(
        cancelText = R.string.cancel,
        submitText = R.string.next,
        onCancel = {},
        onSubmit = {},
        submitAvailable = false,
    )
}

@Preview(showBackground = false)
@Composable
private fun ChoosingButtonsRowPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            ChoosingButtonsRowFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun ChoosingButtonsRowPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            ChoosingButtonsRowFunctionForPreview()
        }
    }
}
