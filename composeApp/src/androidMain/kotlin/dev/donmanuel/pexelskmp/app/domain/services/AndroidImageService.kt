package dev.donmanuel.pexelskmp.app.domain.services

import android.app.WallpaperManager
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import dev.donmanuel.pexelskmp.app.domain.models.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL

class AndroidImageService(
    private val context: Context
) : ImageService {

    override suspend fun downloadImage(photo: Photo): Result<String> = withContext(Dispatchers.IO) {
        try {
            val imageUrl = photo.src.original
            val fileName = "wallpaper_${photo.id}.jpg"
            
            val bitmap = downloadBitmap(imageUrl)
            val savedPath = saveImageToGallery(bitmap, fileName)
            
            Result.success(savedPath)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun setAsWallpaper(photo: Photo): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val imageUrl = photo.src.original
            val bitmap = downloadBitmap(imageUrl)
            
            val wallpaperManager = WallpaperManager.getInstance(context)
            wallpaperManager.setBitmap(bitmap)
            
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun downloadBitmap(imageUrl: String): Bitmap = withContext(Dispatchers.IO) {
        val url = URL(imageUrl)
        val connection = url.openConnection()
        connection.connectTimeout = 10000
        connection.readTimeout = 10000
        
        val inputStream = connection.getInputStream()
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream.close()
        
        bitmap
    }

    private fun saveImageToGallery(bitmap: Bitmap, fileName: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            saveImageToGalleryModern(bitmap, fileName)
        } else {
            saveImageToGalleryLegacy(bitmap, fileName)
        }
    }

    private fun saveImageToGalleryModern(bitmap: Bitmap, fileName: String): String {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        
        uri?.let { fileUri ->
            context.contentResolver.openOutputStream(fileUri)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            }
            return fileUri.toString()
        }
        
        throw IOException("Failed to save image to gallery")
    }

    private fun saveImageToGalleryLegacy(bitmap: Bitmap, fileName: String): String {
        val picturesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val file = File(picturesDir, fileName)
        
        FileOutputStream(file).use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        }
        
        return file.absolutePath
    }
} 