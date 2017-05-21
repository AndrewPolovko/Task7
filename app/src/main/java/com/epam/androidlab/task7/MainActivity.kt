package com.epam.androidlab.task7

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mButton = findViewById(R.id.button_send_new_notification)
        mButton.setOnClickListener {
            startDownload(Constants.TEST_FILE_URL, Constants.TEST_FILE_NAME)
        }
    }

    private fun startDownload(url: String, fileName: String) {
        val downloadIntent = Intent(this, DownloadService::class.java)
        downloadIntent.putExtra(Constants.KEY_FOR_FILE_URL, url)
        downloadIntent.putExtra(Constants.KEY_FOR_FILE_NAME, fileName)
        startService(downloadIntent)
    }
}
