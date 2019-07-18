package anikdas012.anikdas.tk.offlinesynctry.network.retrofit

import anikdas012.anikdas.tk.offlinesynctry.model.ContactModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiEndPoints {

    @GET("/offlineSync")
    fun getContacts(): Call<List<ContactModel>>

    @Headers("Content-Type: application/json")
    @POST("/offlineSync")
    fun addContact(@Body contact: ContactModel): Call<ContactModel>
}