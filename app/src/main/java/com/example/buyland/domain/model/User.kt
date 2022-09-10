package com.example.buyland.domain.model

import android.graphics.Bitmap
import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.ByteArrayInputStream


@Entity
data class User(
    val userName:String,
    val password:String,
    val name:String?=null,
    val profilePicture:Bitmap?=null, // maybe we should change this in the future
    @PrimaryKey (autoGenerate = true) val id :Int? = null,


    )
