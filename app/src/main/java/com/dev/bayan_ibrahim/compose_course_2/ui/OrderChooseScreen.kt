package com.dev.bayan_ibrahim.compose_course_2.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan_ibrahim.compose_course_2.R
import com.dev.bayan_ibrahim.compose_course_2.domain.MenuItem
import com.dev.bayan_ibrahim.compose_course_2.ui.components.ChoosingButtonsRow
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme

@Composable
fun OrderChooseScreen(
    modifier: Modifier = Modifier,
    items: List<MenuItem>,
    selectedItem: MenuItem,
    onSelectItem: (MenuItem) -> Unit,
    onCancel: () -> Unit,
    onSubmit: () -> Unit,
) {
    val nextAvailable = items.contains(selectedItem)
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) {item ->
            SingleOption(
                item = item,
                selected = selectedItem.name == item.name,
                onClick = {
                    onSelectItem(item)
                }
            )
        }
        item {
            Spacer(modifier = Modifier.padding(8.dp))
            if (!nextAvailable) {
                WarningMessage()
            }
            ChoosingButtonsRow(
                cancelText = R.string.cancel,
                submitText = R.string.next,
                onCancel = onCancel,
                onSubmit = onSubmit,
                submitAvailable = nextAvailable
            )
        }
    }
}

@Composable
private fun SingleOption(
    modifier: Modifier = Modifier,
    item: MenuItem,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .selectable(
                selected = selected,
                onClick = onClick,
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(selected = selected, onClick = null)
        Spacer(modifier = Modifier.padding(8.dp))
        SingleOptionData(modifier = Modifier.weight(1f), item = item)
    }
}

@Composable
private fun SingleOptionData(
    modifier: Modifier = Modifier,
    item: MenuItem,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = item.name,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = item.description,
        )
        Text(
            text = item.getFormattedPrice(),
            style = MaterialTheme.typography.caption,
        )
        Divider()
    }
}

@Composable
private fun WarningMessage(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.warning),
            contentDescription = stringResource(R.string.no_option_selected_warning_hint),
            tint = Color.Yellow,
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = stringResource(R.string.no_option_selected_warning),
            style = MaterialTheme.typography.subtitle1,
        )
    }
}

@Composable
private fun OrderChooseScreenFunctionForPreview(
) {
    OrderChooseScreen(
        items = listOf(
            MenuItem.EntreeItem(
                name = "Item Name",
                description = "Item Description",
                price = 9.99
            ),
            MenuItem.EntreeItem(
                name = "Item Name",
                description = "Item Description",
                price = 9.99
            ),
            MenuItem.EntreeItem(
                name = "Item Name",
                description = "Item Description",
                price = 9.99
            ),
        ),
        selectedItem =
        MenuItem.EntreeItem(
            name = "Item Name 2",
            description = "Item Description",
            price = 9.99,
        ),
        onSelectItem = {},
        onCancel = {},
        onSubmit = {},
    )
}
@Preview(showBackground = false)
@Composable
private fun OrderEntreeScreenPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            OrderChooseScreenFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun OrderEntreeScreenPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            OrderChooseScreenFunctionForPreview()
        }
    }
}
