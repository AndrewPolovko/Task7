package com.epam.androidlab.task7

import android.app.DownloadManager
import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment


class DownloadService : IntentService("DownloadService") {

    override fun onHandleIntent(intent: Intent) {
        val fileUrl = intent.getStringExtra(Constants.KEY_FOR_FILE_URL)
        val fileName = intent.getStringExtra(Constants.KEY_FOR_FILE_NAME)

        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse(fileUrl))

        request.setTitle(resources.getString(R.string.file_downloading_title))
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, fileName)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)

        downloadManager.enqueue(request)
    }
}
