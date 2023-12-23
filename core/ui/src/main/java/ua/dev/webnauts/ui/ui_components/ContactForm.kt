package ua.dev.webnauts.ui.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.coroutines.coroutineContext


/**
 * Форма для контактов, которая отображает заголовок и контактную информацию
 * */
@Preview
@Composable
fun ContactForm(title: String = "Name:", contactInfo: String = "Json Strong") {
    Column(
        modifier = Modifier
            .background(
                color = Color(0xFFDBF0F3),
                shape = RoundedCornerShape(size = 10.dp)
            )
            .padding(6.dp)
            .fillMaxWidth(1f),
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 12.sp
            ),
            color = Color(0xFF555555)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(1.dp)
                .background(color = Color(0xFFBBBBBB))
        )
        Text(
            text = contactInfo,
            style = TextStyle(
                fontSize = 16.sp
            ),
            color = Color(0xFF050505)
        )
    }
}