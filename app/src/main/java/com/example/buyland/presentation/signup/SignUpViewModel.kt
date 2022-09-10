package com.example.buyland.presentation.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buyland.domain.model.User
import com.example.buyland.domain.use_cases.UserUseCases
import com.example.buyland.presentation.logIn.LogInEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userUseCases:UserUseCases
):ViewModel() {
    var checkUserName = mutableStateOf(false)





    fun onEvent(event:SingUpEvent , username:String,password:String){
        when(event){
            is SingUpEvent.checkTheUserName ->{
                viewModelScope.launch {
                    try{if(userUseCases.checkUserName(username)){

                        checkUserName.value = true
                    }}catch (e:Exception){Log.e("Error","${e.message}")}



            }}
            is SingUpEvent.insertUser ->{
                viewModelScope.launch{
                    try{

                    userUseCases.insertUser(User(userName=username,password=password ))
                    } catch (e:Exception){
                        Log.e("Error","${e.message}")
                    }
                }
            }
        }
    }
    fun settingCheckUserNameToFalse(){
        checkUserName.value=false
    }
}