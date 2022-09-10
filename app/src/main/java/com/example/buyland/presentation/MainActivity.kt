package com.example.buyland.presentation


import android.app.Application
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import coil.compose.rememberAsyncImagePainter

import com.bumptech.glide.annotation.GlideModule
import com.example.buyland.presentation.Navigation.Navigation

import com.example.buyland.ui.theme.BuylandTheme
import dagger.hilt.android.AndroidEntryPoint
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi

import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.buyland.ConnectivityObserver
import com.example.buyland.NetworkConnectivityObserver
import com.example.buyland.R
import com.example.buyland.presentation.comments.CommentsScreen
import dagger.hilt.android.qualifiers.ApplicationContext
import hilt_aggregated_deps._dagger_hilt_android_internal_modules_ApplicationContextModule

@AndroidEntryPoint
@GlideModule
class MainActivity : ComponentActivity() {

    var backPressedTime: Long = 0

    private var imageUriState = mutableStateOf<Uri?>(null)

    private val selectImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            imageUriState.value = uri
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)








        setContent {
            BuylandTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation(imageUriState = imageUriState, selectImageLauncher = selectImageLauncher, toastMessage =applicationContext)





                }


            }
        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
        if (backPressedTime + 500 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {

        }
        backPressedTime = System.currentTimeMillis()
    }

    }


