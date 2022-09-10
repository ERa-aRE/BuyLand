package com.example.buyland.presentation.signup

import com.example.buyland.domain.model.User
import com.example.buyland.presentation.logIn.LogInEvents

sealed class SingUpEvent{
    data class checkTheUserName(val userName:String):SingUpEvent()
    object insertUser : SingUpEvent()
}



