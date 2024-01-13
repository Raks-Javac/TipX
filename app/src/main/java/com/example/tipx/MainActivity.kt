package com.example.tipx

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipx.component.InputField
import com.example.tipx.ui.theme.TipXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                Column {
                    TopHeader()
                    MainContent()
                }
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    TipXTheme {
        content()
    }

}

//Top header for the tip app
//@Preview(name = "Tip header")
@Composable
fun TopHeader(totalPerPerson: Double = 134.9200) {

    val total = "%.2f".format(totalPerPerson)
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(
                RoundedCornerShape(CornerSize(15.dp))
            ),
        color = MaterialTheme.colorScheme.inversePrimary
    ) {
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Text(
                text = "Total Per Person",
                style = MaterialTheme.typography.headlineMedium
            )


            Text(
                text = "$$total",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun MainContent() {
    BillForm (){ billAmt -> Log.d("AMT","$billAmt")

    }


}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(modifier: Modifier = Modifier, onValueChanged: (String) -> Unit = {}){
    val amount: MutableState<String> = remember {
        mutableStateOf("0.00")
    }

    val validateState = remember(amount.value) {
        amount.value.trim().isNotEmpty()

    }

    val keyboardController = LocalSoftwareKeyboardController.current;

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp)), border = BorderStroke(
            width = 2.dp,
            color = Color.Gray
        )

    ) {


        Column {
            InputField(valueState = amount,
                value = amount.value,
                enabled = true,
                isSingleLine = true,
                label = "Enter Bill",
                onAction = KeyboardActions {
                    if (!validateState) return@KeyboardActions
                    onValueChanged(amount.value.trim())
                    keyboardController?.hide()
                }

            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipXTheme {

    }
}