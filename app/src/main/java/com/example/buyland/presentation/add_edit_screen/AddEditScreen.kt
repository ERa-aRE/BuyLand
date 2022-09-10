package com.example.buyland.presentation.add_edit_screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.buyland.domain.model.Product


@Composable
fun AddEditScreen(navController: NavController,product:Product,viewModel:AddEditViewModel= hiltViewModel()) {
    var thisUserId = -1
    var updatePrice by remember{
        mutableStateOf("")
    }
    var updateDescription by remember{
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxSize()){
        Canvas(modifier = Modifier.size(400.dp), onDraw = {
            drawCircle(color = Color(158, 210, 141), center = Offset(size.width/2, size.height/2))
        })
        Canvas(modifier = Modifier.size(270.dp), onDraw = {
            drawCircle(color = Color(130, 240, 150), center = Offset(size.width/3, size.height*2))
        })
        Canvas(modifier = Modifier.size(230.dp), onDraw = {
            drawCircle(color = Color(120, 250, 120), center = Offset(size.width*2 , size.height*2.5f))
        })


        Column(modifier= Modifier , horizontalAlignment = Alignment.CenterHorizontally){
            TopAppBar(title = { Text("Edit Product") }, backgroundColor = Color.White)
            Spacer(modifier=Modifier.height(58.dp))
            Text(text = "Current Price is : ${product.pPrice} $")
            Text(text = "Current Description is : ${product.pDescription}")
            Spacer(modifier = Modifier.padding(top = 10.dp))
            TextField(value = updatePrice, onValueChange = {updatePrice = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Default),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp), label = {Text(text = "Edit Price")})

            TextField(value = updateDescription , onValueChange ={updateDescription = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp), label = {Text(text = "Edit Description")})

            Spacer(modifier = Modifier.padding(5.dp))
            Row(horizontalArrangement = Arrangement.SpaceEvenly){

                Button(onClick = {
                    if(updateDescription==null || updateDescription == "") updateDescription = product.pDescription
                    if(updatePrice==null || updatePrice== "") updatePrice = product.pPrice
                    if( updateDescription != null && updateDescription !="" && updatePrice !=null && updatePrice != "" )
                    {
                        thisUserId = product.userId
                        viewModel.EditProduct(Product(product.pName,updatePrice,updateDescription,product.userId,product.pId))
                        navController.navigate(route = "HomeScreen"+"/${thisUserId}")
                    }

                },
                    colors = ButtonDefaults.buttonColors(Color(10, 255, 10))
                ) {
                    Text(text = "Update")

                }
                Spacer(modifier = Modifier.padding(5.dp))
                Button(onClick = {
                    thisUserId = product.userId

                    viewModel.DeleteProduct(product)
                    navController.navigate(route = "HomeScreen"+"/${thisUserId}")
                },
                    colors = ButtonDefaults.buttonColors(Color(240, 10, 10))
                ) {
                    Text(text = "Delete")

                }

            }


        }

    }


}

