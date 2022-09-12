package com.example.buyland.presentation.homescreen

import android.content.Context
import android.media.MediaPlayer
import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.buyland.ConnectivityObserver
import com.example.buyland.R
import com.example.buyland.presentation.Navigation.Screens
import com.example.buyland.presentation.componants.Products
import com.example.buyland.presentation.componants.TopProducts

@ExperimentalCoilApi
@Composable
fun HomeScreen(userId:Int,navController: NavController,viewModel: HomeScreenViewModel= hiltViewModel()) {
    val state = viewModel.state.value
    viewModel.getProducts(userId=userId)
    var openDialog by remember { mutableStateOf(false)  }
    val status by viewModel.connectivityObserver.observe().collectAsState(initial =
    ConnectivityObserver.Status.Losing)
    ///best way is to create a sharedRefrence for netWorkAvailability
    val context = viewModel.contextF

    when (status) {
        ConnectivityObserver.Status.Unavailable, ConnectivityObserver.Status.Lost -> {
            /*LaunchedEffect(key1 = true){
            delay(1000L)
        }*/
            val mp : MediaPlayer = MediaPlayer.create(context, R.raw.havat)
            if(status==ConnectivityObserver.Status.Available){mp.stop()}

            Box(modifier = Modifier.fillMaxSize()){

                middleCircles()
                Column(modifier = Modifier
                    .padding(1.dp)
                    .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                    val context = LocalContext.current
                    val imageLoader = ImageLoader.Builder(context)
                        .components {
                            if (SDK_INT >= 28) {
                                add(ImageDecoderDecoder.Factory())
                            } else {
                                add(GifDecoder.Factory())
                            }
                        }
                        .build()


                    if(status==ConnectivityObserver.Status.Available){mp.stop()}


                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(context).data(data = R.drawable.jdance).apply(block = {
                                size(Size.ORIGINAL)
                            }).build(), imageLoader = imageLoader
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(120.dp),
                    )


                    Image(
                        painter = painterResource(R.drawable.shopping_logo),
                        contentDescription = "",
                        modifier = Modifier
                            .size(300.dp)
                            .clickable {
                                mp.start()
                            }
                    )

                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(context).data(data = R.drawable.loadingbrgy).apply(block = {
                                size(Size.ORIGINAL)
                            }).build(), imageLoader = imageLoader
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(80.dp)
                            .clip(CircleShape)
                            .clickable { mp.pause() },
                    )
                    if(status==ConnectivityObserver.Status.Available){mp.stop()}


                }

            }


        }
        ConnectivityObserver.Status.Available -> {

            Box(modifier = Modifier
                .fillMaxSize()) {


                Canvas(modifier = Modifier.size(200.dp), onDraw = {
                    drawCircle(color = Color(158, 210, 141), center = Offset(size.height, 0f))
                })
                Canvas(modifier = Modifier.size(200.dp), onDraw = {
                    drawCircle(color = Color(108, 210, 141), center = Offset(size.height*2, size.width))
                })
                Canvas(modifier = Modifier.size(300.dp), onDraw = {
                    drawCircle(color = Color(50, 230, 80), center = Offset(0f, size.width*2.5f))
                })
                Column(modifier = Modifier.padding(10.dp)) {


                    TopAppBar(title = { Text("Home Page") }, backgroundColor = Color.White)





                    Column(
                        modifier = Modifier.padding(8.dp), Arrangement.Center,
                        Alignment.CenterHorizontally
                    ) {
                        LazyRow {
                            items(state.products) { it ->
                                TopProducts(it.pName,it.pPrice,it.pDescription,it.userId,it.pId,navController=navController) }

                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(state.products) { it ->
                                Products(it.pName,it.pPrice,it.pDescription,it.userId,it.pId,navController=navController)

                            }
                        }


                        BottomNavigation(
                            modifier = Modifier,
                            backgroundColor = Color(180, 234, 44, 255),
                            elevation = 3.dp,
                            //contentColor = Color(180, 234, 44, 255),

                        ) {

                            BottomNavigationItem(
                                selected = false,
                                onClick = {
                                    openDialog = true

                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.SwitchAccount ,
                                        contentDescription = "Sort"
                                    )
                                })
                            BottomNavigationItem(
                                selected = false,
                                onClick = {
                                    navController.navigate(Screens.Comments.route)

                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Comment ,
                                        contentDescription = "Sort"
                                    )
                                })
                            BottomNavigationItem(
                                selected = false,
                                onClick = {
                                    navController.navigate(route = "insert_product/"+"${userId}")
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Sort"
                                    )
                                })


                        }
                    }
                }


            }}
        else -> {
            Column(modifier=Modifier,Arrangement.Center){
                CircularProgressIndicator()
                Text("Please Check your internet connection")
            }

        }
    }


    if(openDialog){
    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
            openDialog = false
        },
        title = {
            Text(text = "Change Account")
        },
        text = {
            Text("Are you sure about changing the current account ? ")
        },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(Color.Green) ,

                onClick = {
                    navController.navigate(route="Log_In")
                    openDialog = false

                }) {
                Text(" Yes ")
            }
        },
        dismissButton = {
            Button(
                colors = ButtonDefaults.buttonColors(Color.Red) ,

                onClick = {
                    openDialog = false
                }) {
                Text("Cancel")
            }
        }
    )}

    }
@Composable
fun middleCircles(){



    // Creating a Canvas to draw a Circle
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawCircle(
            color = Color(0xff0f9d58),
            center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
            radius = size.minDimension/2,
            style = Stroke(15F)
        )
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val arcRadius = 200f
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawArc(
            color = Color(20, 205, 25),
            startAngle = -90f, //start angle is always in clockwise direction
            sweepAngle = 270f, // angle formed between the start angle
            useCenter = false,
            size = androidx.compose.ui.geometry.Size(arcRadius, arcRadius),
            topLeft = Offset(
                (canvasWidth / 2) - (arcRadius / 2),
                (canvasHeight / 2) - (arcRadius / 2)
            ),
            style = Stroke(width = 10f, cap = StrokeCap.Round)
        )
    }

}
