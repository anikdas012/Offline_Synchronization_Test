package anikdas012.anikdas.tk.offlinesynctry.synchronization

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import anikdas012.anikdas.tk.offlinesynctry.R
import anikdas012.anikdas.tk.offlinesynctry.util.AppUtil
import anikdas012.anikdas.tk.offlinesynctry.viewmodel.ContactViewModel

/**
 * Created by "Anik Das" on 7/28/2019
 * Developer email: "anikdas012@gmail.com"
 */
class SyncWorker(context: Context, params: WorkerParameters, viewMode: ContactViewModel): Worker(context, params) {

    val LOG_TAG = "OFFLINE_Sync_Worker"


    override fun doWork(): Result {
        Log.d(LOG_TAG, "doWork")
//        Showing notification while uploading

//        Creating notification channel if android version > O
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Offline Channel"
            val channelDescription = "Contains all notifications from Offline Synchronization"
            val channelImportance = NotificationManager.IMPORTANCE_DEFAULT

            val notificationChannel = NotificationChannel(AppUtil.CHANNEL_ID, channelName, channelImportance)
            notificationChannel.description = channelDescription

            val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }


        val builder = NotificationCompat.Builder(applicationContext, AppUtil.CHANNEL_ID)
            .setSmallIcon(R.drawable.not_synced)
            .setContentTitle("Synchronization going on")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        NotificationManagerCompat.from(applicationContext)
            .notify(AppUtil.NOTIFICATION_ID, builder.build())

        return Result.success()
    }
}