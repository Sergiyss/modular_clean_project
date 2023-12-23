package ua.dev.webnauts.modularcleandesign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import ua.dev.webnauts.modularcleandesign.ui.theme.ModularCleanDesignTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            ModularCleanDesignTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ModularCleanDesignTheme {
        Greeting("Android")
    }
}


@Composable
fun MainScreen(mainViewModel: MainViewModel = hiltViewModel()){

    var test  by remember { mutableStateOf( "Home Screen" ) }
    var text2  by remember { mutableStateOf<Int>( 0 ) }

    LaunchedEffect(key1 = Unit, block = {
        mainViewModel.getUseFlow().collect{
            test = it.lastOrNull()?.usersData.toString()
        }
    })

    Box(modifier = Modifier.fillMaxSize(1f)){
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = text2.toString(), style = TextStyle(fontSize = 20.sp),)
            Text(text = test, style = TextStyle(fontSize = 24.sp),)
            Button(onClick = { text2++ }) {
                Text("GO")

                mainViewModel.user(text2)
            }
        }
    }
}