package anikdas012.anikdas.tk.offlinesynctry.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log

class AppUtil {
    companion object {

        private val LOG_TAG = "OFFLINE_App_Util"

        /**
         * Final status code of synced and unsynced
         * contacts
         */
        const val STATUS_SYNCED: Int = 0
        const val STATUS_UNSYNCED: Int = 1

        /**
         * This method will return current
         * network condition
         *
         * @param context: Application context
         * @return True if internet is connected else False
         */
        fun isNetworkConnected(context: Context): Boolean {
            Log.d(LOG_TAG, "isNetworkConnected")
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            return activeNetwork?.isConnected ?: false
        }
    }
}