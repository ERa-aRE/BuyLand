package com.example.buyland.presentation.homescreen

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.buyland.ConnectivityObserver
import com.example.buyland.NetworkConnectivityObserver
import com.example.buyland.domain.use_cases_product.ProductUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel

class HomeScreenViewModel
@Inject constructor(
    private val productUseCases: ProductUseCases , application : Application
):AndroidViewModel(application){

    private val _state =mutableStateOf(ProductState())
    val state : State<ProductState> = _state
    private var getProductsJob : Job? =null
    val contextF = application.applicationContext

    var connectivityObserver : ConnectivityObserver
    init {
        connectivityObserver = NetworkConnectivityObserver(application.applicationContext)
    }




    fun getProducts(userId:Int){
        getProductsJob?.cancel()
        getProductsJob= productUseCases.getProductsUseCase(userId=userId).
                onEach { products ->
                    _state.value = state.value.copy(
                        products = products ,
                    )
                }.launchIn(viewModelScope)
    }






}