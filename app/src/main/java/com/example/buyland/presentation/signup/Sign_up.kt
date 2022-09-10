package com.example.buyland.presentation.signup

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController


@Composable
fun Sing_up(toastMessage:Context,navController: NavController,viewModel: SignUpViewModel = hiltViewModel()) {
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var repassword by rememberSaveable { mutableStateOf("") }
    var repasswordVisible by rememberSaveable { mutableStateOf(false) }
    var userName by rememberSaveable { mutableStateOf("") }
    var checkedState by rememberSaveable { mutableStateOf(false) }
    val maxCharPassword = 20
    val maxCharUserName =254

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        Canvas(modifier = Modifier.size(250.dp), onDraw = {
            drawCircle(color = Color(158, 210, 141), center = Offset(size.width/2, 0f), radius = 150.dp.toPx())
        })

        Column(
                modifier = Modifier.padding(25.dp),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ) {

            Text(text="Sign Up",color = Color.LightGray,
                fontSize = 20.sp,modifier = Modifier.padding(8.dp)
            )

                TextField(
                    value = userName,
                    onValueChange = {/*if (it.length <= maxCharUserName )*/ userName = it
                        viewModel.settingCheckUserNameToFalse()
                        viewModel.onEvent(SingUpEvent.checkTheUserName(userName),userName ,password)},
                    singleLine =true,
                    label = { Text("Enter Your Email (it will be your username)") },
                    placeholder = { Text("User Name") },
                    modifier=Modifier.widthIn(350.dp,350.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = TextFieldDefaults.textFieldColors(
                        Color(0, 0, 0),
                        backgroundColor = Color.White
                    ),


                    )
                Spacer(modifier = Modifier.padding(8.dp))
                TextField(
                    value = password,
                    onValueChange = { /*if (it.length <= maxCharPassword)*/ password = it },
                    label = { Text("Enter Password") },
                    singleLine = true,
                    placeholder = { Text("") },
                    modifier=Modifier.widthIn(350.dp,350.dp),
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
                TextField(
                    value = repassword,
                    onValueChange = { if (it.length <= maxCharPassword) repassword = it },
                    label = { Text("Re-Enter Password") },
                    singleLine = true,
                    placeholder = { Text("") },
                    modifier=Modifier.widthIn(350.dp,350.dp),
                    visualTransformation = if (repasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image = if (repasswordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        // Please provide localized description for accessibility services
                        val description = if (repasswordVisible) "Hide password" else "Show password"

                        IconButton(onClick = { repasswordVisible = !repasswordVisible }) {
                            Icon(imageVector = image, description)
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        Color(0, 0, 0),
                        backgroundColor = Color.White
                    ),
                )
                Spacer(modifier = Modifier.padding(top=52.dp))
                Row(modifier=Modifier) {
                    Checkbox(
                        checked = checkedState,
                        onCheckedChange = { checkedState = it }
                    )
                    Text("  I agree with terms and conditions",modifier=Modifier)
                }
            Spacer(modifier=Modifier.height(18.dp))

                Button(onClick = {

                    if(!viewModel.checkUserName.value){
                        if(password==repassword && password!=""){
                            viewModel.onEvent(SingUpEvent.insertUser,userName,password)
                            if(checkedState && userName!="" && password!="") navController.navigate("Profile_setup")
                            else Toast.makeText(toastMessage, "please agree to term and conditions", Toast.LENGTH_SHORT).show()

                        }else{Toast.makeText(toastMessage, "password does not match", Toast.LENGTH_SHORT).show()}

                    }else{Toast.makeText(toastMessage, "username already exists", Toast.LENGTH_SHORT).show()}



                    },
                    colors=ButtonDefaults.buttonColors(if(checkedState)Color(158, 210, 141) else Color(200, 200, 200)) ) {
                    Text(text = "Confirm")

                }



        }
        Canvas(modifier = Modifier.size(600.dp), onDraw = {
            drawCircle(color = Color(37, 163, 100), center = Offset(size.width , size.height/1.2f) , radius = 100.dp.toPx())

        })
        Canvas(modifier = Modifier.size(600.dp), onDraw = {
            drawCircle(color = Color(67, 150, 50), center = Offset(0f , size.height/1.09f) , radius = 80.dp.toPx())

        })
    }
}
