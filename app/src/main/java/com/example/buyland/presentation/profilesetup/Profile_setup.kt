package com.example.buyland.presentation.profilesetup


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.buyland.R
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import java.io.ByteArrayOutputStream


@Composable
fun Profile_setup(imageUriState: MutableState<Uri?>,selectImageLauncher:ActivityResultLauncher<String>,navController: NavController) {

    var name by rememberSaveable { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp), Arrangement.Top, Alignment.CenterHorizontally

        ) {
            Text(text="Profile SetUp",color = Color.LightGray,
            fontSize = 20.sp,modifier = Modifier.padding(8.dp)
            )


            Spacer(modifier = Modifier.padding(15.dp))
            if (imageUriState.value != null) {
                val uriImage=rememberAsyncImagePainter(model =imageUriState.value!! )
                Image(painter = uriImage, contentDescription =null , contentScale = ContentScale.Crop,//for auto cropping the image to square
                    modifier=Modifier.clip(CircleShape).size(150.dp).border(2.dp, Color.Green, CircleShape))
            } else Image(
                painter = painterResource(id = R.drawable.user_default_avatar),
                contentDescription = null ,contentScale= ContentScale.Crop, modifier = Modifier.clip(CircleShape).size(150.dp).border(2.dp, Color.Green, CircleShape)
            )


            Button(
                onClick =  { selectImageLauncher.launch("image/*")  },

                colors = ButtonDefaults.buttonColors(Color(158, 210, 141)),
                modifier = Modifier.padding(8.dp)

            ) { Text(text = "Change Avatar") }

            Spacer(modifier = Modifier.padding(25.dp))
            TextField(
                value = name,
                onValueChange = { name = it },
                singleLine = true,
                placeholder = { Text("Name") },
                modifier = Modifier.widthIn(350.dp, 350.dp),
                colors = TextFieldDefaults.textFieldColors(
                    Color(0, 0, 0),
                    backgroundColor = Color.White
                )

            )


            Spacer(modifier = Modifier.padding(30.dp))
            Button(
                onClick = { navController.navigate("Log_In")
                          imageUriState.value = null },
                colors = ButtonDefaults.buttonColors(Color(158, 210, 141))
            ) { Text(text = "Confirm") }

            Canvas(modifier = Modifier.size(600.dp), onDraw = {
                drawCircle(
                    color = Color(37, 163, 100),
                    center = Offset(size.width, size.height / 1.2f), radius = 100.dp.toPx()
                )
            })
            Canvas(modifier = Modifier.size(600.dp), onDraw = {
                drawCircle(
                    color = Color(67, 150, 50),
                    center = Offset(0f, size.height), radius = 180.dp.toPx()
                )
            })
        }
    }
}



