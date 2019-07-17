package anikdas012.anikdas.tk.offlinesynctry.network.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        @Volatile
        private var retrofit: Retrofit? = null
        private  val baseURL = "anikdas.tk/api"

        fun getInstance(): Retrofit {
            if (retrofit == null) {
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}