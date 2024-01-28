package com.example.tipx.Utils

fun calculateTotalTip(totalBill: Double, tipPercentage: Double): Double {
    return if (totalBill > 1 || totalBill.toString()
            .isNotEmpty()
    ) (totalBill * tipPercentage) / 100 else 0.00
}
