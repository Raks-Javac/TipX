package com.example.tipx.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


val IconSizeAdjust: Modifier = Modifier.size(40.dp);

@Composable
fun RoundedButtonWidget(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    tint: Color = Color.Black.copy(alpha = 0.8f),
    elevation: Dp = 4.dp
) {

    Card(
        modifier = modifier
            .padding(4.dp)
            .clickable {
                onClick.invoke()
            }
            .then(IconSizeAdjust),
        shape = CircleShape,
elevation = CardDefaults.cardElevation(
    defaultElevation = elevation),

        ) {
 Column (
     modifier = modifier.fillMaxWidth().fillMaxHeight(),
     horizontalAlignment = Alignment.CenterHorizontally,
     verticalArrangement = Arrangement.Center
     ){
     Icon(

         imageVector = imageVector, contentDescription = "Plus or minus icon",tint= tint)


 }
    }
}