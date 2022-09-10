package com.example.buyland.presentation.logIn

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.buyland.R
import com.example.buyland.presentation.Navigation.Screens


@Composable
fun Log_In(navController:NavController,viewModel: LogInViewModel = hiltViewModel(),toastMessage: Context) {

    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var userName by rememberSaveable { mutableStateOf("") }
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        TopCircles()
        Column(modifier = Modifier.fillMaxSize(),Arrangement.Center,
            Alignment.CenterHorizontally) {

            Text(text="Log In",color = Color.LightGray,
                fontSize = 20.sp,modifier = Modifier.padding(8.dp)
            )

            Column(
                modifier = Modifier.padding(8.dp),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ) {

                TextField(
                    value = userName,
                    onValueChange = { userName = it
                                    viewModel.settingUserToFalse()
                                    viewModel.onEvent(LogInEvents.checkTheUserName(userName),userName)
                                    viewModel.onEvent(LogInEvents.initUser,userName)

                                    },
                    label = { Text("User Name") },
                    placeholder = { Text("User Name") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),

                    colors = TextFieldDefaults.textFieldColors(
                        Color(0, 0, 0),
                        backgroundColor = Color.White
                    )

                    )

                Spacer(modifier = Modifier.padding(8.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it
                                      viewModel.onEvent(LogInEvents.checkThePassword,userName)},
                    label = { Text("Password") },
                    singleLine = true,
                    placeholder = { Text("Password") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        // Please provide localized description for accessibility services
                        val description = if (passwordVisible) "Hide password" else "Show password"

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, description)
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        Color(0, 0, 0),
                        backgroundColor = Color.White
                    ),
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp), Arrangement.Center, Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { navController.navigate("Sing_up") },
                        colors = ButtonDefaults.buttonColors(Color(37, 163, 100))
                    ) { Text(text = "Sign up") }
                    Spacer(modifier = Modifier.padding(end = 8.dp))

                    Button(
                        onClick = {


                            if(viewModel.isUserThere.value){


                                if(viewModel.password.value==password){
                                Toast.makeText(toastMessage, "Welcome to BuyLand !", Toast.LENGTH_SHORT).show()
                                    if(viewModel.userId.value!=null){
                                navController.navigate(route="HomeScreen"+"/${viewModel.userId.value!!}")}}
                                else{Toast.makeText(toastMessage, "Incorrect password", Toast.LENGTH_SHORT).show()}
                                }
                            else{
                                Toast.makeText(toastMessage, "UserName Not Found !", Toast.LENGTH_SHORT).show()
                            }



                                 },






                        colors = ButtonDefaults.buttonColors(Color(158, 210, 141))
                    ) { Text(text = "Log in") }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp), Arrangement.Center
                ) {
                    val uriHandler = LocalUriHandler.current

                    Text(text = "Forgot password?",color = Color.LightGray, modifier = Modifier
                        .clickable {

                            try{
                            uriHandler.openUri("https://www.elitedaily.com/p/why-do-i-always-forget-my-password-science-says-theres-a-method-to-the-madness-11854109")
                        }
                        catch (e: Exception){
                            Log.e(e.toString(), "Failed to open ")
                        }

                        })

                }
                //Text(text="${viewModel.user.value?.userName?:"help"} ////${viewModel.password.value ?:"help"}////${viewModel.userId.value ?:"help"}")



            }

        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.Bottom,Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.shopping_logo),
                contentDescription = "Shopping logo"
            )
        }



    }
}
@Composable
    fun TopCircles() {
    Box {

        Canvas(modifier = Modifier.size(200.dp), onDraw = {
            drawCircle(color = Color(158, 210, 141), center = Offset(0f, 0f))
        })
        Canvas(modifier = Modifier.size(600.dp), onDraw = {
            drawCircle(color = Color(37, 163, 100), center = Offset(size.width , 0f))

        })
    }
}
