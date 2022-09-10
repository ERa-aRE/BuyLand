package com.example.buyland.presentation.insertProduct

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
class InsertProductViewModel
@Inject constructor(
    private val productUseCase : ProductUseCases , application:Application
):AndroidViewModel(application){

    fun insert_P(pName:String,pPrice:String,pDescription:String,userId:Int){
        viewModelScope.launch {
            try {
                if(pName!=null && pPrice !=null && pDescription !=null && userId !=null){
                    productUseCase.insertProduct(Product(pName = pName , pPrice = pPrice , pDescription = pDescription , userId =userId))
                }

            }catch (e:Exception){
                Log.e("Error" , "${e.message}")
            }
        }
    }
}