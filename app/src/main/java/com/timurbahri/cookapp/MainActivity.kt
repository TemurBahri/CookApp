package com.timurbahri.cookapp

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.timurbahri.cookapp.entity.Cooks
import com.timurbahri.cookapp.R
import com.timurbahri.cookapp.ui.theme.CookAppTheme
import com.timurbahri.cookapp.viewmodel.MainPageViewModel
import com.timurbahri.cookapp.viewmodel.MainPageViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PageScreen()
                }
            }
        }
    }
}

@Composable
fun PageScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainscreen" ) {
        composable("mainscreen") {
            MainScreen(navController = navController)
        }
        composable("detail_page/{cook}", arguments = listOf(
            navArgument("cook") {type = NavType.StringType}
        )) {
            val json = it.arguments?.getString("cook")
            val cook = Gson().fromJson(json, Cooks::class.java)
            DetailPage(cook = cook)
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel : MainPageViewModel = viewModel(
        factory = MainPageViewModelFactory(context.applicationContext as Application)
    )
    val cooksList = viewModel.cooksList.observeAsState(listOf())


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Cooks")},
                backgroundColor = colorResource(id = R.color.mainColor),
                contentColor = colorResource(id = R.color.white)

            )
        },
        content = {
            LazyColumn {
                items(
                    count = cooksList.value!!.count(),
                    itemContent = {
                        val cook = cooksList.value!![it]
                        Card(modifier = Modifier
                            .padding(all = 5.dp)
                            .fillMaxWidth()) {
                            Row(modifier = Modifier.clickable {
                                val cookJson = Gson().toJson(cook)
                                navController.navigate("detail_page/${cookJson}")
                            }) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(all = 10.dp)
                                        .fillMaxWidth()
                                ) {
                                    val activity = (LocalContext.current as Activity)
                                    Image(bitmap = ImageBitmap.imageResource(id = activity.resources.getIdentifier(
                                        cook.cook_image_name,"drawable",activity.packageName
                                    )),contentDescription = "", modifier = Modifier.size(100.dp))
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.SpaceEvenly,
                                            modifier = Modifier.fillMaxHeight()
                                        ) {
                                            Text(text = cook.cook_name, fontSize = 20.sp)
                                            Spacer(modifier = Modifier.size(20.dp))
                                            Text(text = "${cook.cook_price} tl", color = Color.Blue )
                                        }
                                        Icon(painter = painterResource(id = R.drawable.arrow_ic),
                                            contentDescription ="" )
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CookAppTheme {

    }
}