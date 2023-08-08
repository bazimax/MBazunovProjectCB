package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import com.example.jetpackcompose.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)) {
                itemsIndexed(
                    listOf(
                        ItemRowModel(1, "Миша", "Тест 123 ткарошпршвао прваошпрв пролвапр апрвопрв пр порлавплор ваорплва пврап ловро" +
                                "аолывоаваоы  аовыдла оывдл аоывалдыво ады" +
                                "роавыаорывлоар  аыв рлаоы раоывр а ыарыло арыло"),
                        ItemRowModel(2, "Саша", "Тест"),
                        ItemRowModel(3, "Марина", "Тест 123 ткарошпршвао прваошпрв пролвапр апрвопрв пр порлавплор ваорплва пврап ловро" +
                                "аолывоаваоы"),
                        ItemRowModel(4, "Кирилл", "Тест 123 ткарошпршвао прваошпрв пролвапр апрвопрв пр порлавплор ваорплва пврап ловро" +
                                "аолывоаваоы  аовыдла оывдл аоывалдыво ады" +
                                "роавыаорывлоар  аыв рлаоы раоывр а ыарыло арыло"),
                        ItemRowModel(5, "Александр", "Тест"),
                    )
                ) {_, item ->
                    MyRow(item = item)
                }
            }
            /*Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(rememberScrollState())) {
                for (i in 0..10) {
                    Text(text = "Hello $i!")
                }
            }*/

            //NewText()
            //Text(text = "Hello there!")
            /*JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }*/
        }
    }
}

/*@Composable
private fun ListItem(name: String, prof: String){
    var counter = remember {
        mutableStateOf(0)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                counter.value++
            },
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box{
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(64.dp)
                        .clip(shape = RoundedCornerShape(10.dp)),
                )
                Column(modifier = Modifier.padding(start = 5.dp)
                    ) {
                    Text(text = counter.value.toString())
                    Text(text = prof)
                }
            }
        }
    }
}*/

/*@Preview(showBackground = true)
@Composable
fun NewText() {
    Text(text = "Hello alone24!")
}*/

/*
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTheme {
        Greeting("Android")
    }
}*/
