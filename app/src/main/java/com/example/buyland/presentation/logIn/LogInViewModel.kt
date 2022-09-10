package com.example.buyland.presentation.logIn

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buyland.ConnectivityObserver
import com.example.buyland.NetworkConnectivityObserver
import com.example.buyland.domain.model.User
import com.example.buyland.domain.use_cases.UserUseCases
import com.example.buyland.domain.use_cases_product.ProductUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class



LogInViewModel @Inject constructor(
    private val userUseCases : UserUseCases, application: Application,

    ):AndroidViewModel(application){


    var isUserThere = mutableStateOf(false)
    var userId = mutableStateOf<Int?>(null)
    var user = mutableStateOf<User?>(null)
    var password = mutableStateOf<String?>(null)





    fun onEvent(event : LogInEvents,userName:String ){
        when(event){
            is LogInEvents.checkTheUserName -> {
                viewModelScope.launch{
                    try{if(userUseCases.checkUserName(userName)){
                        isUserThere.value = true}




                    }catch (e:Exception){
                        Log.e("Error","${e.message}")
                    }

                }

            }
            is LogInEvents.initUser ->{
                viewModelScope.launch {
                    try {
                        if(userName!=null && userName !="")
                            userId.value = userUseCases.getIdByName(userName)
                    }catch (e:Exception){
                        Log.e("Error","${e.message}")
                    }
                }

            }
            is LogInEvents.checkThePassword ->{
                viewModelScope.launch {
                    try {
                        if(userName!=null && userName !="")
                        password.value = userUseCases.getPasswordById(userId.value?:1)

                    }catch (e:Exception){
                        Log.e("Error","${e.message}")
                    }
                }
            }


            }
        }






    fun settingUserToFalse(){
        isUserThere.value=false
    }

}

