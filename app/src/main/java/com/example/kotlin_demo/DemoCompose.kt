package com.example.kotlin_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
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

@Preview(showBackground = true)
@Composable
fun TextPreview2() {
    KotlindemoTheme {
        TextWithSize("demo text 11".repeat(10), 20.sp,
        Color.Green
        )


    }
}