package com.example.buyland.presentation.comments

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.buyland.data.repository.BuylandRepositoryImpl
import com.example.buyland.di.AppModule
import com.example.buyland.domain.api_use_cases.GetCommentUseCase
import com.example.buyland.domain.api_use_cases.PostCommentUseCase
import com.example.buyland.domain.model.NetResponse
import com.example.buyland.domain.model.PostComment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class CommentsViewModel @Inject constructor(
    val getComments : GetCommentUseCase,
    val postComment: PostCommentUseCase,


     application: Application,

): AndroidViewModel(application) {

    private val _state = mutableStateOf<CommentsState>(CommentsState())
    val state : State<CommentsState> = _state
    private var showJob: Job? = null
    var myResponse :MutableLiveData<NetResponse?> = MutableLiveData()
    @SuppressLint("StaticFieldLeak")
    val context = application.applicationContext
    var result : MutableState<String> = mutableStateOf<String>("nothing yet")

    init{
        showComments()
    }

    fun showComments(){
        showJob?.cancel()
        showJob = viewModelScope.launch {
            getComments().onEach {
                _state.value = state.value.copy(
                    commentsItems = it?: emptyList()
                )

            }.launchIn(this)


        }

    }
    ////
    fun pushingComment(pc:PostComment){
        viewModelScope.launch {
            try {

                //postComment(pc.TextNazar_)
                val call: Call<String?>? = postComment(pc.TextNazar_,pc.NumRank)

                call!!.enqueue(object: Callback<String?>{


                    override fun onFailure(call: Call<String?>, t: Throwable) {
                        Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                        result.value = "Error found is : " + t.message
                    }

                    override fun onResponse(
                        call: Call<String?>,
                        response: Response<String?>
                    ) {
                        // this method is called when we get response from our api.
                        Toast.makeText(context, "Data posted to API", Toast.LENGTH_SHORT).show()
                        // we are getting a response from our body and
                        // passing it to our model class.
                        val model:String? = response.body()
                        // on below line we are getting our data from model class
                        // and adding it to our string.
                        val resp =
                            "Response Code : " + response.code() + "  $model"
                        // below line we are setting our string to our response.
                        result.value = resp
                    }
                })



            }catch (e:HttpException){
                Log.e("Error","${e.message}")
            }catch(e: IOException){
                Log.e("Error","${e.message}")
            }
        }
    }






}