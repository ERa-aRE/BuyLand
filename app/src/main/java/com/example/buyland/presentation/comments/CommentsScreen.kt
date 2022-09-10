package com.example.buyland.presentation.comments

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.map
import com.example.buyland.R
import com.example.buyland.domain.model.Comment
import com.example.buyland.domain.model.PostComment

@Composable
fun CommentsScreen(viewModel: CommentsViewModel = hiltViewModel()) {

    val state = viewModel.state.value
    var nazar = remember{mutableStateOf("")}
    var score = remember{mutableStateOf("")}
    Column(horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.Center,modifier=Modifier.padding(8.dp)){
        TextField(value = nazar.value, onValueChange ={nazar.value=it},
            label = { Text("Comment") },
            placeholder = { Text("Comment") },
            colors = TextFieldDefaults.textFieldColors(
                Color(0, 0, 0),
                backgroundColor = Color.White
            ))
        score.value=RatingBarPost(rating = 0)

        Button(onClick = {
            viewModel.pushingComment(PostComment(TextNazar_ = nazar.value,NumRank =score.value))
            

        }) {
            Text("ثبت نظر")

        }
        Text(text = viewModel.result.value)






        LazyColumn(modifier= Modifier
            .fillMaxSize()
            .padding(8.dp)){
            items(state.commentsItems.size){ it ->
                val comment = state.commentsItems[it]
                CommentItem(comment =comment)
                if(it<state.commentsItems.size-1){
                    Divider()
                }


            }
        }
    }

}

@Composable
fun CommentItem(comment : Comment,modifier:Modifier=Modifier) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clickable{ },
        elevation = 5.dp) {
        Column(modifier = Modifier.padding(5.dp)) {
            Text(text=comment.User_Name + ":")
            Row(modifier=modifier.fillMaxWidth(),Arrangement.SpaceBetween){
                RatingBar(rating = comment.NumRank)
                Text(text=comment.DateCreate)

            }
            Text(text = comment.TextNazar,modifier=Modifier.fillMaxWidth(), textAlign = TextAlign.End)

        }
        
    }


}

@Composable
fun RatingBarPost(modifier:Modifier=Modifier,
            rating:Int):String {
    var ratingState by remember {
        mutableStateOf(rating)
    }
    if (rating>6){ ratingState = 6}

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 6 downTo 1){
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_star_rate_24),
                contentDescription = "star",
                modifier = modifier
                    .width(48.dp)
                    .height(48.dp)
                    .clickable {
                        ratingState = i
                    },
                tint = if (i <= ratingState) Color(0xFFFFD700)else Color(0xFFA2ADE1)
            )
        }

    }
    return ratingState.toString()
}
@Composable
fun RatingBar(modifier:Modifier=Modifier,
              rating:Int) {
    var ratingState by remember {
        mutableStateOf(rating)
    }
    if (rating>6){ ratingState = 6}
    if (rating<1){ ratingState = 1}


    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.End
    ) {
        for (i in 6 downTo 1){
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_star_rate_24),
                contentDescription = "star",
                modifier = modifier
                    .width(22.dp)
                    .height(22.dp),

                tint = if (i <= ratingState) Color(0xFFFFD700)else Color(0xFFA2ADE1)
            )
        }

    }
}