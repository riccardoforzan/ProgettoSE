package com.epse.gallery.screen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.epse.gallery.SPUtils
import com.epse.gallery.ui.theme.GalleryTheme

class Settings(private val ctx: Context, private val navController: NavHostController) {

    //Contains the name of the gallery
    var textState = mutableStateOf(TextFieldValue(""))

    @Composable
    fun ShowSettings() {
        GalleryTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Settings") },
                        navigationIcon = {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(Icons.Filled.ArrowBack, contentDescription = null)
                            }
                        },
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text="Set your gallery name")

                    //Set a placeholder with the value saved on shared preferences
                    val sp = ctx.getSharedPreferences(SPUtils.preferences, Context.MODE_PRIVATE)
                    val actual = sp.getString(SPUtils.gallery_title,"")!!
                    Text(text="Actual name: $actual")

                    OutlinedTextField(
                        value = textState.value,
                        onValueChange = {
                            textState.value = it

                        },
                        label = {Text("Set your gallery name")},
                    )

                    Text("New gallery name: " + textState.value.text)

                    Button(
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = MaterialTheme.colors.onPrimary
                        ),
                        onClick = {
                            //Update on shared preferences
                            val sp = ctx.getSharedPreferences(SPUtils.preferences, Context.MODE_PRIVATE)
                            with(sp.edit()) {
                                //Save the text value
                                putString(SPUtils.gallery_title, textState.value.text)
                                apply()
                            }
                        }
                    ){
                        Text(
                            text = "Change name"
                        )
                    }
                }

            }
        }
    }


}