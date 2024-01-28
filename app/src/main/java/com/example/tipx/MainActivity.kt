package com.example.tipx

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
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
import com.example.tipx.Utils.calculateTotalTip
import com.example.tipx.component.InputField
import com.example.tipx.ui.theme.TipXTheme
import com.example.tipx.widgets.RoundedButtonWidget
import java.util.logging.Logger

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
            .padding(30.dp)
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
    BillForm() { billAmt ->
        Log.d("AMT", "$billAmt")

    }


}


@Preview
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(modifier: Modifier = Modifier, onValueChanged: (String) -> Unit = {}) {
    val amount: MutableState<String> = remember {
        mutableStateOf("")
    }

    val validateState = remember(amount.value) {
        amount.value.trim().isNotEmpty()

    }

    val splitCounter = remember {
        mutableStateOf(1)
    }
    val sliderValue = remember {
        mutableStateOf(0f)
    }
    val tipPercentage = (sliderValue.value *100).toInt()
    val totalBill = remember {
        mutableStateOf(0.00)
    }

    val tipAmount = remember {
        mutableStateOf(0.00)
    }


    val range = IntRange(start = 1, endInclusive = 100)
    val keyboardController = LocalSoftwareKeyboardController.current;

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp)), border = BorderStroke(
            width = 1.dp,
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


            Row(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Split",
                    modifier = Modifier.align(
                        alignment = Alignment.CenterVertically

                    )
                )
                Spacer(modifier = Modifier.width(120.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.End

                ) {
                    RoundedButtonWidget(
                        imageVector = Icons.Default.Remove, onClick = {
                            splitCounter.value =
                                if (splitCounter.value > 1) splitCounter.value - 1 else 1
                            Log.d("RDbutton", "Remove")
                        })

                    Text(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .align(Alignment.CenterVertically),
                        text = "${splitCounter.value}",
                    )
                    RoundedButtonWidget(
                        imageVector = Icons.Default.Add, onClick = {
                            splitCounter.value =
                                if (splitCounter.value < range.last) splitCounter.value + 1 else splitCounter.value
                            Log.d("RDbutton", "Add")
                        })


                }

            }
            Spacer(modifier = Modifier.height(5.dp))
            //Tip Section
            Row(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tip",
                    modifier = Modifier.align(
                        alignment = Alignment.CenterVertically

                    )
                )
                Spacer(modifier = Modifier.width(200.dp))
                Text(
                    text = "${tipAmount.value}",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

            }

            //percentage section
            Spacer(modifier = Modifier.height(5.dp))
            //Tip Section
            Row(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Percentage",
                    modifier = Modifier.align(
                        alignment = Alignment.CenterVertically

                    )
                )
                Spacer(modifier = Modifier.width(150.dp))
                Text(
                    text = "${tipPercentage}%",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

            }


            //Slider section
            Slider(
                modifier = Modifier.padding(horizontal = 20.dp),
                value = sliderValue.value,
                onValueChange = {


                        newValue ->
                    sliderValue.value = newValue

                    tipAmount.value = calculateTotalTip(
                        totalBill =
                        amount.value.toDouble(),
                        tipPercentage =
                        tipPercentage.toDouble()
                    )

                },
                steps = 5,

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