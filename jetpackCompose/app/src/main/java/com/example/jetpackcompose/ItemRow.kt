package com.example.jetpackcompose

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MyRow(item: ItemRowModel) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    /*val isExpandVal = remember {
        mutableStateOf(false)
    }*/
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(3.dp)
        .background(Color.White)
    ) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(3.dp)
            .size(64.dp)
            .clip(CircleShape)
        )
        Column(modifier = Modifier.padding(start = 10.dp, top = 15.dp)) {
            Text(text = item.title)
            Text(text = item.content,
            modifier = Modifier
                /*.horizontalScroll(rememberScrollState())*/
                .clickable {
                isExpanded = !isExpanded
                    /*isExpandVal.value = !isExpandVal.value*/
            },
            maxLines = if(isExpanded) Int.MAX_VALUE else 1)
            /*maxLines = if(isExpandVal.value) Int.MAX_VALUE else 1)*/
        }



    }
}