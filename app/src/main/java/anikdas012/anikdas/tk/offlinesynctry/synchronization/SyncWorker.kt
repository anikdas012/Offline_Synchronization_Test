package anikdas012.anikdas.tk.offlinesynctry.synchronization

import android.content.Context
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
//        Showing notification while uploading
        val builder = NotificationCompat.Builder(applicationContext, AppUtil.CHANNEL_ID)
            .setSmallIcon(R.drawable.not_synced)
            .setContentTitle("Synchronization going on")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        NotificationManagerCompat.from(applicationContext)
            .notify(AppUtil.NOTIFICATION_ID, builder.build())
        
    }
}