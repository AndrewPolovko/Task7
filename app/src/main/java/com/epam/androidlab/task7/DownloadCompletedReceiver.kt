package com.epam.androidlab.task7

import android.app.DownloadManager
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.content.Intent
import android.net.Uri
import android.support.v7.app.NotificationCompat

class DownloadCompletedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val fileId = intent.extras.getLong(DownloadManager.EXTRA_DOWNLOAD_ID)
        val fileUri = getFileUri(context, fileId)
        showDownloadCompletedNotification(context, fileUri)
    }

    private fun getFileUri(context: Context, fileId: Long): Uri {
        val mDownloadManager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val uri = mDownloadManager.getUriForDownloadedFile(fileId)
        return uri
    }

    private fun showDownloadCompletedNotification(context: Context, fileUri: Uri) {
        val intentToViewGif = Intent(context, GifActivity::class.java)
        intentToViewGif.putExtra(Constants.KEY_FOR_DOWNLOADED_FILE_URI, fileUri.toString())
        val mNotificationBuilder = NotificationCompat.Builder(context)
                .setContentTitle(context.resources.getString(R.string.file_downloaded_title))
                .setContentText(context.resources.getString(R.string.file_downloaded_description))
                .setSmallIcon(R.drawable.ic_offline_pin_black_24dp)
                .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_LIGHTS)
                .setContentIntent(PendingIntent.getActivity(context, 0, intentToViewGif, PendingIntent.FLAG_UPDATE_CURRENT))
                .setAutoCancel(true)

        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(0, mNotificationBuilder.build())
    }
}
