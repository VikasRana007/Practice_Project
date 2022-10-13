package com.learningacademy.bizcarddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learningacademy.bizcarddemo.ui.theme.BizCardDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCardDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    CreateVikasCard()
                }
            }
        }
    }
}

@Composable
fun CreateVikasCard() {
    val buttonClickedState = remember {
        // this property is used to remember the state of button is clicked or not.
        mutableStateOf(false)
    }
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            backgroundColor = Color.White,
            elevation = 4.dp
        ) {
            // Column is basically used to isolate the surface components separately.
            Column(modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
                CreateImageProfile(modifier = Modifier.size(100.dp))
                Divider()
                CreateInfo()
                Button(onClick = {
                    buttonClickedState.value = !buttonClickedState.value
                }) {
                    Text("Portfolio",
                        style = MaterialTheme.typography.button)
                }

                if (buttonClickedState.value) {
                    Content()
                } else {
                    Box{}
                }
            }
        }
    }
}

@Composable
private fun Content() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(5.dp)) {
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, Color.LightGray)) {

            Portfolio(data = listOf("Project 1",
                "Project 2",
                "Project 3",
                "Project 4",
                "Project 5",
                "Project 6",
                "Project 7"))

        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data) { item ->
            Card(modifier = Modifier
                .padding(13.dp)
                .fillMaxWidth(),
                shape = RectangleShape,
                elevation = 4.dp) {
                Row(modifier = Modifier
                    .padding(7.dp)
                    .background(MaterialTheme.colors.surface)
                    .padding(7.dp)) {
                    CreateImageProfile(modifier = Modifier.size(70.dp))
                    Column(modifier = Modifier
                        .padding(7.dp)
                        .align(alignment = CenterVertically)) {
                        Text(item, fontWeight = FontWeight.Bold)
                        Text("A Great Project",
                            style = MaterialTheme.typography.body2)
                    }
                }
            }
        }
    }

}


@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(modifier = modifier
        .size(150.dp)
        .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)) {  // that shows the visibility of the object. which is in alpha

        Image(painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "profile image",
            modifier = modifier,
            contentScale = ContentScale.Crop)
    }
}




@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text("Vikas R.",
            color = MaterialTheme.colors.primaryVariant,
            style = MaterialTheme.typography.h4)
        Text("Android Compose Developer", modifier = Modifier.padding(0.5.dp))
        Text("@TheMilesCompose",
            modifier = Modifier.padding(4.dp),
            style = MaterialTheme.typography.subtitle1)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizCardDemoTheme {
        CreateVikasCard()
    }
}