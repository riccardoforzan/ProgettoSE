package com.epse.gallery.screen

import android.app.Activity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.epse.gallery.R
import com.google.accompanist.coil.rememberCoilPainter

/**
 * This composable UI is shown when the app is started but the permissions are not granted to it.
 * The purpose of this UI is asking for permissions respecting the policy of Android.
 * https://developer.android.com/training/permissions/requesting
 *
 * This screen is displayed when it's detected that the application hasn't permissions to work.
 * The user decides to grant (or not to) permissions.
 * If not, there are in this class composable function to display an error message.
 */

@ExperimentalFoundationApi
class SetupScreen(
    private val ctx: Activity, private val navController: NavHostController) {

    /**
     * Take inspiration from
     * https://github.com/AppIntro/AppIntro
     */
    @Composable
    fun AskForStorage(){
            MaterialTheme {
                val typography = MaterialTheme.typography
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        /**
                         * TODO:
                         * Insert application logo as request parameter
                         */
                        painter = rememberCoilPainter(request = null),
                        contentDescription = null,
                        modifier = Modifier
                            .height(120.dp)
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(4.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.height(16.dp))

                    Text(
                        text=ctx.getString(R.string.permission_read_external_storage_description),
                        style = typography.h6
                    )

                    Button(
                        modifier = Modifier.padding(30.dp),
                        onClick = {
                            /**
                             * TODO: Ask for permissions
                             */
                            navController.navigate(Screens.ImagesGridShowGrid)
                        }
                    ){
                        Text(
                            text = ctx.getString(R.string.btn_manage_permissions),
                        )
                    }
                    
                }
            }

    }

    /**
     * Screen to show when read on external storage permission has not been granted
     */
    @Composable
    fun ReadStorageDenied(){
        MaterialTheme{
            val typography = MaterialTheme.typography
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    ctx.getString(R.string.permission_read_external_storage_not_granted),
                    style = typography.h6
                )
            }
        }
    }

}