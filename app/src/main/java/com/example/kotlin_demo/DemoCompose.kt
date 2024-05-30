package com.example.kotlin_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin_demo.ui.theme.KotlindemoTheme

class DemoCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlindemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RenderColum()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Xin Chào: $name!",
        modifier = modifier,
        color = Color.Green,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
}
@Composable
fun RenderColum(names: List<String> = listOf("nguyen van a", "nguyen van b", "nguyen van c")) {
    val count = rememberSaveable { mutableStateOf(0) }
    Column {
        Text(
            text = "${count.value}",
            color = Color.Green,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
        )
        Button(onClick = { count.value++ }) {
            Text("Click me")
        }
        for (name in names) {
            Text(
                text = "Xin Chào: $name",
                color = Color.Green,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    KotlindemoTheme {
        RenderColum()
    }
}
/**
 * Text
 */

@Composable
fun TextWithSize(label : String, size : TextUnit, color: Color) {
    SelectionContainer {
    Text(label, fontSize = size, color = color,
        fontWeight = FontWeight.Thin,
        maxLines = 2
        )
    }
}

@Composable
fun SimpleButton() {
    Button(onClick = {},
        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
//        shape = RectangleShape
        border = BorderStroke(2.dp, Color.Green),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        )
        ) {
        Image(
            painterResource(id = R.drawable.baseline_shopping_cart_24),
            contentDescription ="Cart button icon",
            modifier = Modifier.size(20.dp).padding(
                end = 5.dp
            ))
        Text(text = "Simple Button")
    }
}


@Composable
fun SimpleImage() {
    Image(
        painter = painterResource(id = R.drawable.image_1),
        contentDescription = "t-shirt",
//        modifier = Modifier.fillMaxWidth()
//        modifier = Modifier
//            .size(128.dp),
//
//            .clip(RoundedCornerShape(10)) // clip to the circle shape
//            .border(1.dp, Color.Green, RoundedCornerShape(10))//optional
        modifier = Modifier
            .size( 200.dp)
            .background(Color.DarkGray)
            .padding(20.dp)
    )
}


@Composable
fun SimpleRow() {
    Column(
        modifier = Modifier.fillMaxWidth().height(200.dp).background(Color.DarkGray),
//        verticalAlignment = Alignment.Center,
//        horizontalArrangement = Arrangement.SpaceEvenly
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Row Text 1", Modifier.background(Color.Red))
        Text(text = "Row Text 2", Modifier.background(Color.White))
        Text(text = "Row Text 3", Modifier.background(Color.Green))
    }
}


@Preview(showBackground = true)
@Composable
fun TextPreview2() {
    KotlindemoTheme {
//        TextWithSize("demo text 11".repeat(10), 20.sp,
//        Color.Green
//        )
//        SimpleButton()
//        SimpleImage()
        SimpleRow()
    }
}