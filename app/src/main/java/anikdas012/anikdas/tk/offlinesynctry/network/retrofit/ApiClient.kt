package anikdas012.anikdas.tk.offlinesynctry.network.retrofit

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {

        private val LOG_TAG = "OFFLINE_Api_Client"

        @Volatile
        private var retrofit: Retrofit? = null
        private  val baseURL = "https://anikdas.tk/api/"

        /**
         * This method will return an instance of retrofit
         * in singleton pattern
         */
        private fun getInstance(): Retrofit {
            Log.d(LOG_TAG, "getInstance")
            if (retrofit == null) {
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient().newBuilder().build())
                    .build()
            }
            return retrofit!!
        }


        /**
         * This method will return Api end points
         * to connect with server
         */
        fun getApi(): ApiEndPoints {
            Log.d(LOG_TAG, "getApi")
            return getInstance().create(ApiEndPoints::class.java)
        }
    }
}