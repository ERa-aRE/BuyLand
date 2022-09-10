package com.example.buyland.presentation.componants


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.core.util.toHalf
import androidx.navigation.NavController
import com.example.buyland.presentation.Navigation.Screens
import kotlin.random.Random


@Composable
fun TopProducts(pName:String,pPrice:String,pDescription:String,userId:Int,pId:Int?,navController: NavController) {

    val pDes = if(pDescription.length>20)pDescription.subSequence(0,20) else pDescription



    Column(modifier = Modifier.padding(8.dp).border(1.dp, MaterialTheme.colors.primary,)
        .width(150.dp).height(110.dp).background(color=Color.White).clickable{


            if(pId!=null){
                navController.navigate(route="add_edit_screen/"+"${userId}/"+"${pName}/"+"${pPrice}/"+"${pDescription}/"+"${pId}")}

        }
    ) {
        Text(modifier=Modifier.padding(8.dp),text="${pName}",style=MaterialTheme.typography.h5, color = Color(0xff49B265))
        Text(modifier=Modifier.align(Alignment.End),text="${pPrice} $",style=MaterialTheme.typography.h6,color = Color(0xFF159947))
        Text(text="${pDes}" ,style=MaterialTheme.typography.body1 , maxLines = 1,color = Color(
            37,
            240,
            113,
            255
        )
        )

    }
}
