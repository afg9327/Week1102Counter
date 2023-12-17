package com.example.week1102counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week1102counter.ui.theme.Week1102CounterTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = ViewModelProvider(this)[CounterViewModel::class.java]
        val vm2 = ViewModelProvider(this)[ClickerViewModel::class.java]
        super.onCreate(savedInstanceState)
        setContent {
            //MyApp(content = { Greeting("test") })
            MyApp {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Clicker(vm2)

                    Counter(vm)

                    Counter(vm)
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    Week1102CounterTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background

        ) {
            content()
        }
    }
}

@Composable
fun Clicker(viewModel: ClickerViewModel) {
    val content by viewModel.content.observeAsState(initial = "눌러주세요")
    //var txtString by remember { mutableStateOf("눌러주세요") }
    //val (txtString, setTxtString) = remember { mutableStateOf("눌러주세요") }
    Column(modifier = Modifier
        .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = content,
            fontSize = 70.sp,
        )
        Button(modifier = Modifier
            .fillMaxWidth(),
            onClick = {
            viewModel.onPushed()
            //setTxtString("눌렸습니다")
            }) {
            // Text(text = "눌러봐")
            Text("눌러봐")
        }
    }
}
@Composable
fun Counter(viewModel: CounterViewModel){
    val count by viewModel.count.observeAsState(0)
    //val (count, setcount ) = remember { mutableStateOf(0) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "$count",
            fontSize = 70.sp)
        Row {
            Button(modifier = Modifier.weight(1f), onClick = {
                viewModel.onAdd()
            }) {
                Text(text = "증가")
            }
            Button(modifier = Modifier.weight(1f), onClick = {
                viewModel.onSub() })
            {
                Text(text = "감소")
            }
        }
    }
}
