package com.example.tipx.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Money
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputField(
    modifier : Modifier = Modifier,
    valueState: MutableState<String>,
    value: String,
    enabled: Boolean,
    keyBoard: KeyboardType = KeyboardType.Number,
    isSingleLine: Boolean,
    label: String,
    imeAction: ImeAction = ImeAction.Default,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    OutlinedTextField(
        value = value,
        onValueChange = { valueState.value = it },
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = keyBoard, imeAction = imeAction),
        keyboardActions = onAction,
        modifier = modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.background),
        singleLine = isSingleLine,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Money,
                contentDescription = "Money Icon"
            )
        },
        enabled = enabled,
    )
}

