package com.example.buyland.presentation.Navigation

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.buyland.domain.model.Product
import com.example.buyland.presentation.add_edit_screen.AddEditScreen
import com.example.buyland.presentation.comments.CommentsScreen
import com.example.buyland.presentation.homescreen.HomeScreen
import com.example.buyland.presentation.insertProduct.InsertProduct
import com.example.buyland.presentation.logIn.Log_In
import com.example.buyland.presentation.profilesetup.Profile_setup
import com.example.buyland.presentation.signup.Sing_up
import com.example.buyland.presentation.splash_screen.Splash_screen
import kotlin.system.exitProcess


@Composable
fun Navigation(imageUriState: MutableState<Uri?>, selectImageLauncher: ActivityResultLauncher<String>,toastMessage: Context,) {
    val activity = (LocalContext.current as Activity)
    val navController = rememberNavController()
    var counter = 0
    NavHost(navController = navController, startDestination = "Splash_screen"){
        composable("Splash_screen") {
            Splash_screen(navController = navController)
        }
        composable("Log_In") {
            Log_In(navController = navController,toastMessage=toastMessage)
            BackHandler(enabled = true) {
                if(counter<1){
                    Toast.makeText(toastMessage,"Please Press Back Again To Exit", Toast.LENGTH_SHORT).show()
                }
                counter++
                //onBackPressed(toastMessage,finish,activity)






            }
        }
        composable("Sing_up") {
            Sing_up(toastMessage,navController = navController)


        }
        composable("Profile_setup"){
            Profile_setup(imageUriState = imageUriState, selectImageLauncher = selectImageLauncher,navController = navController)
        }
        composable(route=Screens.HomePage.route,
        arguments=listOf(navArgument("userId"){
            type = NavType.IntType
        })){entry->
            HomeScreen(navController=navController,userId= entry.arguments?.getInt("userId") ?: -1 ,)
            BackHandler(enabled = true) {
                //onBackPressed(toastMessage,finish,activity)
                if(counter<1){
                    Toast.makeText(toastMessage,"Please Press Back Again To Exit", Toast.LENGTH_SHORT).show()
                }
                counter++





            }
        }
        composable(route = Screens.InsertProduct.route,
            arguments=listOf(navArgument("userId"){
                type = NavType.IntType
            })){
            entry->
            InsertProduct(navController=navController,userId=entry.arguments?.getInt("userId") ?: -1)

            }
        //composable("Log_in"){Log_in()}
        composable(route=Screens.AddEditScreen.route,
        arguments= listOf(navArgument("userId"){
            type=NavType.IntType
        },
        navArgument("pname"){
            type = NavType.StringType
        },navArgument("pprice"){
                type = NavType.StringType
            },navArgument("pdescription"){
                type = NavType.StringType
            },
        navArgument("pid"){
            type = NavType.IntType
        })){
            entry ->
            AddEditScreen(navController = navController, product = Product(pName = entry.arguments?.getString("pname")?:"1",
            pPrice = entry.arguments?.getString("pprice")?:"1",
            pDescription = entry.arguments?.getString("pdescription")?:"1",
            userId =entry.arguments?.getInt("userId") ?: -1 ,
            pId = entry.arguments?.getInt("pid")?:-2))
        }
        composable(route=Screens.Comments.route){
            CommentsScreen()
        }

    }

}

var doubleBackToExitPressedOnce = false
fun onBackPressed(toastMessage:Context,finish: () -> Unit,activity: Activity){
    if(doubleBackToExitPressedOnce){
        onBackPressed(toastMessage,finish,activity)
        activity?.finish()
        return
    }


    Toast.makeText(toastMessage,"Please Press Back Again To Exit", Toast.LENGTH_SHORT).show()
    doubleBackToExitPressedOnce = true


}
