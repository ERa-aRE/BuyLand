package com.example.buyland.presentation.componants

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.buyland.presentation.Navigation.Screens
import kotlin.random.Random


@Composable
fun Products(pName:String,pPrice:String,pDescription:String,userId:Int,pId:Int?,navController: NavController) {

        Column(modifier = Modifier.padding(8.dp).border(1.dp, MaterialTheme.colors.primary,)
            .fillMaxWidth().background(color=Color.White).clickable{


            if(pId!=null){
                navController.navigate(route="add_edit_screen/"+"${userId}/"+"${pName}/"+"${pPrice}/"+"${pDescription}/"+"${pId}")}

        }
        ) {
            Text(modifier=Modifier.padding(8.dp),text="${pName}",maxLines = 1,style=MaterialTheme.typography.h4, color = Color(0xFF159947))
            Text(modifier=Modifier.align(Alignment.End),text="${pPrice} $",style=MaterialTheme.typography.h5,color = Color(0xFF159947))
            Text(text="  ${pDescription}",style=MaterialTheme.typography.body2 , maxLines = 1,color = Color(0xFF159947))

        }
    }


