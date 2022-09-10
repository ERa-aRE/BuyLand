package com.example.buyland.presentation.splash_screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.buyland.R
import kotlinx.coroutines.delay


@Composable
fun Splash_screen(navController: NavController) {
    val scale = remember{
        Animatable(0f)
    }
    LaunchedEffect(key1 = true ){
        scale.animateTo(
            targetValue = 1.3f,
            animationSpec = tween(durationMillis = 2000, easing = {OvershootInterpolator(3f).getInterpolation(it)})
        )
        delay(50)
        navController.navigate("Log_In")
    }

    Box(contentAlignment = Alignment.Center, modifier= Modifier.fillMaxSize()){
        Column {
            Image(
                painter = painterResource(id = R.drawable.shopping_logo),
                contentDescription = "BuyLand's logo", /*modifier = Modifier.scale(scale.value)*/
            )
            Row(modifier=Modifier.padding(20.dp), horizontalArrangement = Arrangement.Center) {
                Text(text = " BUY",color = Color(37, 163, 100), modifier = Modifier.padding(end=25.dp))

                Text(text = " LAND",color=Color(158, 210, 141))


            }
        }

    }
}