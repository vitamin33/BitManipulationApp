package com.serbyn.bitmanipulationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.serbyn.bitmanipulationapp.ui.theme.BitManipulationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BitManipulationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val model = MainViewModel()
                    CurrencyChooserFields(model)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BitManipulationAppTheme {
        Greeting("Android")
    }
}


@Composable
fun CurrencyChooserFields(viewModel: MainViewModel) {
    val first = remember {
        mutableStateOf("")
    }
    val second = remember {
        mutableStateOf("")
    }

    val state: Int by viewModel.uiState.collectAsStateLifecycleAware()


    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 7.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Please enter two integers for comparing:",
                    fontSize = 20.sp
                )
            }
            Row {
                OutlinedTextField(
                    value = first.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f),
                    onValueChange = { first.value = it},
                )
                OutlinedTextField(
                    value = second.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f),
                    onValueChange = { second.value = it},
                )
            }

            Button(
                modifier = Modifier
                    .padding(16.dp),
                onClick = { viewModel.calculateDiff(first.value, second.value) }) {
                Text(text = "Calculate", fontSize = 16.sp)
            }

            Text(
                modifier = Modifier
                    .padding(16.dp),
                fontSize = 18.sp,
                text = "Result:"
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                modifier = Modifier
                    .padding(16.dp),
                fontSize = 18.sp,
                text = state.toString()
            )
        }
    }
}