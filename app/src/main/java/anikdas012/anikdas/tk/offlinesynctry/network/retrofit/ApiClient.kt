package anikdas012.anikdas.tk.offlinesynctry.network.retrofit

import retrofit2.Retrofit

class ApiClient {

    companion object {
        @Volatile
        private var retrofit: Retrofit? = null
        private  val baseURL = "anikdas.tk/api"
    }
}