package com.example.buyland.presentation.Navigation

sealed class Screens(val route: String) {
    object LogIn : Screens(route = "Log_In")
    object Comments: Screens(route="Comments")
    object HomePage:Screens(route = "HomeScreen/{userId}"){
        fun passUserId(userId:Int):String{
            return "HomeScreen/$userId"
        }
    }
    object InsertProduct:Screens(route = "insert_product/{userId}")
    object AddEditScreen:Screens(route = "add_edit_screen/{userId}/{pname}/{pprice}/{pdescription}/{pid}")
}