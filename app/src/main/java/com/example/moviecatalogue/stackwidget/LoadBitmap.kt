package com.example.moviecatalogue.stackwidget

import android.content.Context

import android.graphics.BitmapFactory
import android.graphics.Bitmap
import com.example.moviecatalogue.R
import java.net.URL


object LoadBitmap {
    fun loadBit(context: Context, vararg urls: String): Bitmap? {
        val imageURL = urls[0]
        var bimage: Bitmap?
        try {
            val url = URL(imageURL)
            val conn = url.openConnection()
            conn.connectTimeout = 5000
            conn.readTimeout = 50000
            val inp = conn.getInputStream()

            URL(imageURL).openStream()
            bimage = BitmapFactory.decodeStream(inp)
        } catch (e: Exception) {
            bimage = BitmapFactory
                .decodeResource(context.resources, R.drawable.nopicture)
        }

        return bimage
    }
}