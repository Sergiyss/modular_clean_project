package ua.dev.webnauts.homs.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.thumbnail.ThumbnailPlugin


@Preview
@Composable
fun CardCharacter(
    avatarCharacter: String = "",
    nameCharacter: String = "",
    lifeStatus : String = "",
    locationCharacter: String = "",
    firstSeenIn : String = "",

) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.Start),
        verticalAlignment = Alignment.Top,
    ) {

        CoilImage(
            imageModel = { avatarCharacter }, // loading a network image or local resource using an URL.
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            ),
            component = rememberImageComponent {
                +ThumbnailPlugin(IntSize(30 ,30))
            },
            modifier = Modifier.size(160.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(text = nameCharacter,
                style = TextStyle(
                    fontSize = 24.sp
                ),
                color = Color(0xFF555555)
            )
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
                verticalAlignment = Alignment.Top,
            ) {
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .width(10.dp)
                        .background(Color.Red, shape = MaterialTheme.shapes.extraLarge)
                )

                Text(text = lifeStatus,
                    style = TextStyle(
                        fontSize = 16.sp
                    ),
                    color = Color(0xFF555555)
                )
            }

            BlockColumn("Последнее известное местоположение:", locationCharacter)

            BlockColumn("Впервые был замечен в:", firstSeenIn)

        }
    }
}

@Composable
private fun BlockColumn(title : String, locationCharacter: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 14.sp
            ),
            color = Color(0xFF555555)
        )

        Text(
            text = locationCharacter,
            style = TextStyle(
                fontSize = 16.sp
            ),
            color = Color(0xFF555555)
        )
    }
}