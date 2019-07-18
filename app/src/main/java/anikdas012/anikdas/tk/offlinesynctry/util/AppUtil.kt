package anikdas012.anikdas.tk.offlinesynctry.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class AppUtil {
    companion object {
        final val STATUS_SYNCED: Int = 0
        final val STATUS_UNSYNCED: Int = 1

        /**
         * This method will return current
         * network condition
         */
        fun isNetworkConnected(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            return activeNetwork?.isConnected ?: false
        }
    }
}