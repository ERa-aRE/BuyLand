package com.example.buyland.presentation.add_edit_screen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.buyland.domain.model.Product
import com.example.buyland.domain.use_cases_product.ProductUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddEditViewModel
@Inject constructor(
    private val productUseCases: ProductUseCases , application: Application
):AndroidViewModel(application){


    fun EditProduct(product:Product){
        viewModelScope.launch {
            try {
                productUseCases.insertProduct(product)

            }catch (e:Exception){
                Log.e("Error","${e.message}")
            }


        }

    }



    fun DeleteProduct(product:Product){
        viewModelScope.launch {
            try {
                productUseCases.deleteProductUseCase(product)

            }catch (e:Exception){
                Log.e("Error","${e.message}")
            }


        }

    }





}