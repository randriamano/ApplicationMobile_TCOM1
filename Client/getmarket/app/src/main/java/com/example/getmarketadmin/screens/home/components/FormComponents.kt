package com.example.getmarketadmin.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.getmarketadmin.ui.theme.Bg40
import com.example.getmarketadmin.ui.theme.BgButtonColor
import com.example.getmarketadmin.ui.theme.ButtonColor

@Composable
fun TextFieldComponent(productName: String,
                       label : String,
                       onProductNameChange: (String) -> Unit,
                       placeholderText: String){
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = productName,
            onValueChange = onProductNameChange,
            placeholder = { Text(text = placeholderText) },
            colors =
            TextFieldDefaults.colors(
                focusedContainerColor = BgButtonColor,
                unfocusedIndicatorColor = ButtonColor,
                focusedIndicatorColor = ButtonColor,
                unfocusedContainerColor = BgButtonColor,

                )
        )
    }
}


@Composable
fun MultipleTextFieldComponent(productName: String,
                       label : String,
                       onProductNameChange: (String) -> Unit,
                       placeholderText: String){
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = productName,
            onValueChange = onProductNameChange,
            placeholder = { Text(text = placeholderText) },
            singleLine = false,
            maxLines = 2,
            colors =
            TextFieldDefaults.colors(
                focusedContainerColor = BgButtonColor,
                unfocusedIndicatorColor = ButtonColor,
                focusedIndicatorColor = ButtonColor,
                unfocusedContainerColor = BgButtonColor,

                )
        )
    }
}

@Composable
fun NumberFieldComponent(
    numberOfDosage: String,
    onNumberOfDosageChange: (String) -> Unit,
    label: String,
    suffix: String,
    placeholderText: String
){
    Column(
      //  verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            modifier = Modifier.width(128.dp),
            value = numberOfDosage,
            onValueChange = onNumberOfDosageChange,
            placeholder = { Text(text = placeholderText) },
            suffix = { Text(text = suffix) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors =
            TextFieldDefaults.colors(
                focusedContainerColor = BgButtonColor,
                unfocusedIndicatorColor = ButtonColor,
                focusedIndicatorColor = ButtonColor,
                unfocusedContainerColor = BgButtonColor,
                )
        )
    }
}


@Composable
fun CategoryDropdownComponent(
    selectedOptionText: String,
    onSelectionChange: (String) -> Unit,
    options: List<String>,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
) {

    Column(
    ) {
        Text(
            text = "Categorie",
            style = MaterialTheme.typography.bodyLarge
        )

        BoxWithConstraints {
            val maxWidth = this.maxWidth

            TextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = {},
                trailingIcon = {
                    IconButton(onClick = { onExpandedChange(!expanded) }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors =
                TextFieldDefaults.colors(
                    focusedContainerColor = BgButtonColor,
                    unfocusedIndicatorColor = ButtonColor,
                    focusedIndicatorColor = ButtonColor,
                    unfocusedContainerColor = BgButtonColor,

                    )
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { onExpandedChange(false)},
                modifier = Modifier
                    .background(Bg40)
                    .width(maxWidth)
            ) {
                options.forEach { text ->
                    DropdownMenuItem(
                        text = { Text(text) },
                        onClick = {
                            onSelectionChange(text)
                            onExpandedChange(false)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SizeSelectionComponent(
    sizes: List<String>,
    selectedSizes: List<Boolean>,
    onSizeSelected: (Int, Boolean) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp, 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Taille: ",
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            sizes.forEachIndexed { index, size ->
                Checkbox(
                    checked = selectedSizes.getOrNull(index) ?: false,
                    onCheckedChange = { isChecked ->
                        onSizeSelected(index, isChecked)
                    }
                )
                Text(
                    text = size,
                    modifier = Modifier.clickable { onSizeSelected(index, !(selectedSizes.getOrNull(index) ?: false)) }
                )
            }
        }
    }
}

@Composable
fun ButtonComponent(label : String, enable: Boolean){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
          //  .align(Alignment.CenterHorizontally),
        onClick = { },
        colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
        shape = MaterialTheme.shapes.extraLarge,
        enabled = true
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SizeSelectionComponent(
    sizeList: List<String>,
    selectedSizes: List<Boolean>,
    onSizeSelected: (Int) -> Unit
) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            sizeList.forEachIndexed { index, size ->
                FilterChip(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    onClick = { onSizeSelected(index) },
                    selected = selectedSizes.getOrNull(index) ?: false,
                    label = { Text(text = size) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = BgButtonColor,
                        iconColor = ButtonColor),
                    leadingIcon = {
                        if (selectedSizes.getOrNull(index) == true) {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done icon",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        } else {
                            null
                        }
                    }
                )
            }
        }

}

