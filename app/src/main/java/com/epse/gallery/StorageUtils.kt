package com.epse.gallery

import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.PermissionChecker

class StorageUtils {

    companion object {

        /**
         * Boolean function that checks if the application has the READ_EXTERNAL_STORAGE
         * permission granted
         * @param context: Context of the application
         * @return true if the permission is granted, false otherwise
         */
        fun hasReadStoragePermission(context: Context): Boolean {
            var permission =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                } else {
                    PermissionChecker.checkSelfPermission(
                        context,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                }
            return permission == PackageManager.PERMISSION_GRANTED
        }

        /**
         * Boolean function that checks if the application has the READ_EXTERNAL_STORAGE
         * permission granted
         * @param context: Context of the application
         * @return true if the permission is granted, false otherwise
         */
        fun hasWriteStoragePermission(context: Context): Boolean {
            var permission =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                } else {
                    PermissionChecker.checkSelfPermission(
                        context,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                }
            return permission == PackageManager.PERMISSION_GRANTED
        }

        /**
         * This function returns the images saved inside the storage of the smartphone.
         * REQUIRES READ ACCESS TO THE STORAGE
         * @param context: context of the application, used to query the internal storage
         * @return ArrayList containing image URIs
         * @throws SecurityException if the permission to read the storage has not been granted
         */
        fun getImageURIs(context: Context): ArrayList<Uri> {

            /**
             * Checking permission
             * if permission to read the storage has not been granted throw SecurityException
             */
            if (!hasReadStoragePermission(context = context))
                throw SecurityException(context.getString(R.string.permission_read_external_storage_not_granted))

            /**
             * TODO:
             * Find a way to cache this array and rebuild it only if the dataset changes
             */
            val imagesURIs: ArrayList<Uri> = ArrayList()

            /**
             * Setting un the query
             */
            val columns = arrayOf(MediaStore.Images.Media._ID)
            val orderBy = MediaStore.Images.Media.DATE_TAKEN

            var imageCursor: Cursor? = context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns,
                null, null, "$orderBy DESC"
            )

            var columnIndex = imageCursor!!.getColumnIndex(MediaStore.Images.Media._ID)

            while (imageCursor!!.moveToNext()) {
                var id = imageCursor.getLong(columnIndex)
                var imageUri =
                    ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                imagesURIs.add(imageUri)
            }

            return imagesURIs
        }

        /**
         * This function returns the number of images found in the device storage
         * @param context: context of the application, used to query the internal storage
         * @return number of images found
         * @throws SecurityException if the permission to read the storage has not been granted
         */
        fun numberOfImages(context: Context): Int{
            return getImageURIs(context).size
        }

        /**
         * This function is used to populate an empty gallery (for example the emulator gallery)
         * Downloads from "https://picsum.photos/300/300" the specified number images
         * and saves them on the internal storage.
         * REQUIRES NETWORK ACCESS AND WRITE ACCESS TO THE STORAGE (this permission should always be
         * available because is not marked as dangerous)
         * @param number number of images to download
         */
        fun downloadDummyImages(context: Context, number: Int) {

            /**
             * Checking permission
             * if permission to write the storage has not been granted throw SecurityException
             */
            if (!hasWriteStoragePermission(context = context))
                throw SecurityException(context.getString(R.string.permission_write_external_storage_not_granted))

            /**
             * TODO:
             * Download images and save those on the external storage
             */

        }

    }


}