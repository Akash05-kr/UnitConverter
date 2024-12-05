 package com.example.unitconverter

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.U
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.security.AllPermission
import kotlin.math.roundToInt


 class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

 @Composable
 fun UnitConverter(  modifier: Modifier = Modifier) {
     var inputValue by remember { mutableStateOf(" ") }
     var outputValue by remember { mutableStateOf(" ") }
     var inputUnit by remember { mutableStateOf( "Meters")  }
     var outputUnit by remember { mutableStateOf("Meters") }
     var iExpended by remember { mutableStateOf(false) }
     var oExpended by remember { mutableStateOf(false) }
     val conversionFactor = remember { mutableStateOf( 0.01) }
     val oConversionFactor = remember { mutableStateOf( 0.01) }

     fun convertUnits(){
         val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0 // elvis operator
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
         outputValue = result.toString()

     }


     Column(
         modifier = modifier
             .fillMaxSize(),

         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally

     )
     {
            Text(
                "Unit Converter",
                fontSize = 30.sp,
                modifier = modifier.padding(start =  30.dp)
            )
         Spacer(modifier = Modifier.height(26.dp))
         OutlinedTextField(
             value = inputValue,
             onValueChange = { inputValue = it
                             convertUnits()},
             label = {Text("Enter Value")},
             modifier = modifier.fillMaxWidth()
                 .padding(start = 20.dp , end = 20.dp)
         )
         Spacer(modifier = Modifier.height(20.dp))
         Row {
             // Input Box
             Box {
                 // Input Button
                 Button(onClick = { iExpended = true }, modifier = modifier.padding(end =8.dp)
                     .width(170.dp)
                     .height(40.dp)) {
                     Text(inputUnit)
                     Text(text = "   \u1401")


                 }
                 DropdownMenu(expanded = iExpended , onDismissRequest = { iExpended = false }) {
                     DropdownMenuItem(
                         text = { Text( "Centimeters")},
                         onClick = {
                             iExpended = false
                             inputUnit = "Centimeters"
                             conversionFactor.value  = 0.01
                                convertUnits()
                         }
                     )
                     DropdownMenuItem(
                         text = { Text( "Meters")},
                         onClick = {
                             iExpended = false
                             inputUnit = "Meters"
                             conversionFactor.value  = 1.0
                             convertUnits()
                         }
                     )
                     DropdownMenuItem(
                         text = { Text( "Millimeter")},
                         onClick = {
                             iExpended = false
                             inputUnit = "Milli meter"
                             conversionFactor.value  = 0.001
                             convertUnits()
                         }
                     )

                     DropdownMenuItem(
                         text = { Text( "Feet")},
                         onClick = {
                             iExpended = false
                             inputUnit = "Feet"
                             conversionFactor.value  = 0.3048
                             convertUnits()
                         }
                     )
                     DropdownMenuItem(
                         text = { Text( "Inches")},
                         onClick = {
                             iExpended = false
                             inputUnit = "Inches"
                             conversionFactor.value  = 0.0254
                             convertUnits()
                         }
                     )
                 }

             }

             Box {
                 Button(onClick = { oExpended = true},
                     // Output button

                     modifier = modifier.run {
                     padding(start =8.dp)
                                .width(170.dp)
                                .height(40.dp)
                 }) {
                     Text(outputUnit)
                     Text(text = "  \u1401")
                     DropdownMenu(expanded = oExpended , onDismissRequest = { oExpended = false}) {
                         DropdownMenuItem(
                             text = { Text( "Centimeters")},
                             onClick = {
                                 oExpended = false
                                 outputUnit = "Centimeters"
                                 oConversionFactor.value  = 0.01
                                 convertUnits()
                             }
                         )
                         DropdownMenuItem(
                             text = { Text( "Meters")},
                             onClick = {
                                 oExpended = false
                                 outputUnit = "Meters"
                                 oConversionFactor.value  = 1.00
                                 convertUnits()
                             }
                         )
                         DropdownMenuItem(
                             text = { Text( "Milli meter")},
                             onClick = {
                                 oExpended = false
                                 outputUnit = "Milli meter"
                                 oConversionFactor.value  = 0.001
                                 convertUnits()
                             }
                         )

                         DropdownMenuItem(
                             text = { Text( "Feet")},
                             onClick = {
                                 oExpended = false
                                 outputUnit = "Feet"
                                 oConversionFactor.value  = 0.3048
                                 convertUnits()
                             }
                         )
                         DropdownMenuItem(
                             text = { Text( "Inches")},
                             onClick = {
                                 oExpended = false
                                 outputUnit = "Inches"
                                 oConversionFactor.value  = 0.0254
                                 convertUnits()

                             }
                         )
                     }

                 }

             }
         }
         Spacer(modifier = Modifier.height(26.dp))
         Text("Result: $outputValue Meters",
             fontSize = 25.sp,
             )
     }
 }


 @Preview(showBackground = true)
 @Composable
 private fun UnitConverterPreview() {
        UnitConverter()
 }