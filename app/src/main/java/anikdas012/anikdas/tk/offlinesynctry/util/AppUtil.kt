package anikdas012.anikdas.tk.offlinesynctry.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import anikdas012.anikdas.tk.offlinesynctry.database.Contact
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class AppUtil {
    companion object {

        private val LOG_TAG = "OFFLINE_App_Util"

        /**
         * Final status code of synced, unsynced
         * contacts and notification channel id
         * and notification id, background task
         * name, background task data.
         */
        const val STATUS_SYNCED: Int = 0
        const val STATUS_UNSYNCED: Int = 1
        const val CHANNEL_ID: String = "Offline Worker Notification"
        const val NOTIFICATION_ID: Int = 12
        const val BACKGROUND_TASK_NAME = "Sync work"
        const val BACKGROUND_TASK_DATA = "unScynedContacts"

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

        /**
         * This method will convert a List<Contact>
         * to a String object of json.
         *
         * @param contact: List of Contact objects
         * @return Json object of the list
         */
        fun contactListToJson(contact: List<Contact>) = Gson().toJson(contact)

        /**
         * This method will convert a Json
         * to a List<Contact>.
         *
         * @param contact: Json object of Contacts
         * @return List of Contact objects
         */
        fun jsonToContactList(contact: String): List<Contact>{
            val type = object :TypeToken<List<Contact>>(){}.type
            return Gson().fromJson(contact, type)
        }
    }
}