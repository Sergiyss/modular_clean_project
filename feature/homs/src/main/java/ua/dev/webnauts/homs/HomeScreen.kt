package ua.dev.webnauts.homs


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import ua.dev.webnauts.network.data.randomuser.RandomUserResponse
import ua.dev.webnauts.network.ktor.NetworkResponse
import ua.dev.webnauts.ui.ui_components.ContactForm
import ua.dev.webnauts.ui.ui_components.dialog.CustomDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: HomeViewModel = hiltViewModel()) {

    var randomUser by viewModel.randomUser

    var test by remember { mutableStateOf("Home Screen") }
    // var text2 by remember { mutableStateOf<Int>(0) }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getRandomUser()
    })
    CustomDialog(showDialog = true, content = {
        ResetWarning({})
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

                    viewModel.getRandomUser()

                    //   mainViewModel.user(text2)
                }) {
                    Text("NEXT USER")
                }
            }
        }
    )
}


@Composable
fun ResetWarning(
    onDismissRequest: () -> Unit,
) {
    Column(Modifier.background(MaterialTheme.colorScheme.surface)) {

        var graphicVisible by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) { graphicVisible = true }

        AnimatedVisibility(
            visible = graphicVisible,
            enter = expandVertically(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                expandFrom = Alignment.CenterVertically,
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xE9EE8888),
                                Color(0xFFE4BD79),
                            )
                        )
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    imageVector = Icons.Rounded.Warning,
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                )
            }
        }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(modifier = Modifier.height(8.dp))
            Text(
                text = "Reset Data",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
            Box(modifier = Modifier.height(8.dp))
            Text(text = "All your data will be permanently lost and phone will be listed for auction.")
        }
        Row(
            modifier = Modifier.height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onDismissRequest() }
                    .weight(1f)
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "CANCEL", fontWeight = FontWeight.Bold)
            }

            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .width(2.dp)
                    .fillMaxHeight()
                    .background(
                        MaterialTheme.colorScheme.onSurface.copy(alpha = .08f),
                        shape = RoundedCornerShape(10.dp)
                    )
            )

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onDismissRequest() }
                    .weight(1f)
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "RESET", fontWeight = FontWeight.Bold, color = Color(0xFFFF332C))
            }
        }
    }
}