package com.example.buyland.presentation.logIn

import com.example.buyland.domain.model.User

sealed class LogInEvents{
    data class checkTheUserName(val userName:String):LogInEvents()
    object initUser:LogInEvents()
    object checkThePassword:LogInEvents()
}
