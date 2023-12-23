package ua.dev.webnauts.modularcleandesign

import android.os.Bundle
import android.util.Size
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import dagger.hilt.android.AndroidEntryPoint
import ua.dev.webnauts.modularcleandesign.ui.theme.ModularCleanDesignTheme
import ua.dev.webnauts.network.data.randomuser.RandomUserResponse
import ua.dev.webnauts.network.ktor.NetworkResponse
import ua.dev.webnauts.ui.ui_components.ContactForm

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            ModularCleanDesignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(mainViewModel: MainViewModel = hiltViewModel()) {

    var randomUser by mainViewModel.randomUser

    var test by remember { mutableStateOf("Home Screen") }
    // var text2 by remember { mutableStateOf<Int>(0) }

    LaunchedEffect(key1 = Unit, block = {
        mainViewModel.getRandomUser()


        mainViewModel.getUseFlow().collect {
            test = it.lastOrNull()?.usersData.toString()
        }

    })

    Scaffold(modifier = Modifier.fillMaxSize(1f),
        content = {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,

                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(1f),
            ) {
                Text(
                    text = "RANDOM USER",
                    style = TextStyle(
                        fontSize = 24.sp
                    ),
                )
                when (randomUser) {
                    is NetworkResponse.Loading -> {
                        CircularProgressIndicator()
                    }

                    is NetworkResponse.Success -> {
                        (randomUser as NetworkResponse.Success<RandomUserResponse>).data.results[0]?.let {
                            Row(modifier = Modifier.fillMaxWidth(1f),
                                horizontalArrangement = Arrangement.spacedBy(
                                    15.dp,
                                    Alignment.Start
                                ),
                                verticalAlignment = Alignment.Top,
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(.3f),
                                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                                    horizontalAlignment = Alignment.Start,
                                ) {

                                    Image(
                                        painter = rememberAsyncImagePainter(
                                            ImageRequest.Builder(LocalContext.current)
                                                .data(data = it.picture.large)
                                                .apply(block = fun ImageRequest.Builder.() {
                                                    size(128)
                                                }).build(),

                                            ),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(128.dp),
                                        contentScale = ContentScale.Crop
                                    )

                                    ContactForm(
                                        title = "Age :",
                                        contactInfo = "${it.dob.age} ${it.name.first}"
                                    )
                                }

                                Column(
                                    modifier = Modifier.fillMaxWidth(1f),
                                    verticalArrangement = Arrangement.spacedBy(
                                        10.dp,
                                        Alignment.Top
                                    ),
                                    horizontalAlignment = Alignment.Start,
                                ) {


                                    ContactForm(
                                        title = "Name :",
                                        contactInfo = "${it.name.last} ${it.name.first}"
                                    )

                                    ContactForm(
                                        title = "Phone :",
                                        contactInfo = it.phone
                                    )
                                    ContactForm(
                                        title = "Email :",
                                        contactInfo = it.email
                                    )

                                }
                            }
                        }

                    }

                    else -> {
                        Text("Error ${randomUser}")
                        CircularProgressIndicator()
                    }
                }


//                Text(text = text2.toString(), style = TextStyle(fontSize = 20.sp))
//                Text(text = test, style = TextStyle(fontSize = 24.sp))
                Button(onClick = {
                    // text2++

                    mainViewModel.getRandomUser()

                    //   mainViewModel.user(text2)
                }) {
                    Text("NEXT USER")
                }
            }
        }
    )
}

