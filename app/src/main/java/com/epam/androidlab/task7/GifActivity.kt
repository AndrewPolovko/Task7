package com.epam.androidlab.task7

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide

class GifActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif)
        supportActionBar?.hide()

        val gifUri = Uri.parse(intent.getStringExtra(Constants.KEY_FOR_DOWNLOADED_FILE_URI))

        val mGifView = findViewById(R.id.gif_view) as ImageView
        Glide.with(this).load(gifUri).into(mGifView)
    }
}
