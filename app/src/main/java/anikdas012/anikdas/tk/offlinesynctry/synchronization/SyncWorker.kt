package anikdas012.anikdas.tk.offlinesynctry.synchronization

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Created by "Anik Das" on 7/28/2019
 * Developer email: "anikdas012@gmail.com"
 */
class SyncWorker(context: Context, params: WorkerParameters): Worker(context, params) {

    val LOG_TAG = "Sync_Worker"


    override fun doWork(): Result {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}