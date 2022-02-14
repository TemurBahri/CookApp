package com.timurbahri.cookapp

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timurbahri.cookapp.entity.Cooks
import com.timurbahri.cookapp.R

@Composable
fun DetailPage(cook: Cooks) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = cook.cook_name) },
                backgroundColor = colorResource(id = R.color.mainColor),
                contentColor = colorResource(id = R.color.white)
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val activity = (LocalContext.current as Activity)
                Image(bitmap = ImageBitmap.imageResource(id = activity.resources.getIdentifier(
                    cook.cook_image_name,"drawable",activity.packageName
                )),contentDescription = "", modifier = Modifier.size(250.dp))
                Text(text = "${cook.cook_price} tl", color = Color.Blue, fontSize = 50.sp )

                Button(onClick = { Toast.makeText(context,"Sipariş Verildi!",Toast.LENGTH_LONG).show() },
                    modifier = Modifier.size(250.dp,50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.mainColor),
                        contentColor = colorResource(id = R.color.white)
                    )

                    ) {
                    Text(text = "Add to cart")
                }
            }
        }
    )
}