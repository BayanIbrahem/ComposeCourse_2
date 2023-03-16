package com.dev.bayan_ibrahim.compose_course_2.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme

@Composable
fun LabelValueText(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    oneLine: Boolean,
    textSize: TextUnit = 18.sp,
) {
    if (oneLine) {
        Column(
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
        ) {
            Row {
                Text(
                    text = "$label: ",
                    fontWeight = FontWeight.Bold,
                    fontSize = textSize,
                )
                Text(
                    text = value,
                    fontWeight = FontWeight.Normal,
                    fontSize = textSize,
                )
            }
            Divider(modifier = Modifier.padding(top = 8.dp))
        }
    } else {
        Column(
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "$label: ",
                fontWeight = FontWeight.Bold,
                fontSize = textSize,
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = value,
                fontWeight = FontWeight.Normal,
                fontSize = textSize,
            )
            Divider(modifier = Modifier.padding(top = 8.dp))
        }
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun LabelValueTextFunctionForPreview() {
    LabelValueText(label = "Label", value = "this is the value", oneLine = false)
}

@Preview(showBackground = false)
@Composable
private fun LabelValueTextPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            LabelValueTextFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun LabelValueTextPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            LabelValueTextFunctionForPreview()
        }
    }
}
