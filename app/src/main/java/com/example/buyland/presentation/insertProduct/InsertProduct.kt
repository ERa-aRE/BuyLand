package com.example.buyland.presentation.insertProduct

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.buyland.domain.use_cases_product.InsertProduct

@Composable
fun InsertProduct(navController: NavController,userId:Int,viewModel: InsertProductViewModel = hiltViewModel()) {
    var pName by rememberSaveable { mutableStateOf("") }
    var pPrice by rememberSaveable { mutableStateOf("") }
    var pDescription by rememberSaveable { mutableStateOf("") }

    Box(modifier=Modifier.fillMaxSize()){
        Canvas(modifier = Modifier.size(400.dp), onDraw = {
            drawCircle(color = Color(158, 210, 141), center = Offset(size.width/2, size.height/2))
        })
        Canvas(modifier = Modifier.size(270.dp), onDraw = {
            drawCircle(color = Color(130, 240, 150), center = Offset(size.width/3, size.height*2))
        })
        Canvas(modifier = Modifier.size(230.dp), onDraw = {
            drawCircle(color = Color(120, 250, 120), center = Offset(size.width*2 , size.height*2.5f))
        })
        Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            TopAppBar(title = { Text("Add Product") }, backgroundColor = Color.White)
            Spacer(modifier=Modifier.height(58.dp))
            TextField(value = pName, onValueChange ={pName = it} ,label = { Text("Product's Name") },
                modifier = Modifier.fillMaxWidth())
            TextField(value = pPrice, onValueChange ={pPrice = it},label = { Text("Product's Price")} ,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Default),
                modifier = Modifier.fillMaxWidth())
            TextField(value = pDescription, onValueChange ={pDescription = it},label = { Text("Product's Description")},
            modifier = Modifier.fillMaxWidth())
            Spacer(modifier=Modifier.height(16.dp))




        }
        Button(modifier=Modifier.align(Alignment.BottomCenter).fillMaxWidth(),onClick = {
            if(pName!="" && pPrice!="" && pDescription!= ""){
                viewModel.insert_P(pName,pPrice,pDescription,userId)
                navController.navigate(route = "HomeScreen/"+"${userId}")}
        }) {
            Text(text = "Insert")

        }

    }







    
}
