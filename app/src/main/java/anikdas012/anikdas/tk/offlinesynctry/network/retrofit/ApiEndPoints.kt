package anikdas012.anikdas.tk.offlinesynctry.network.retrofit

import anikdas012.anikdas.tk.offlinesynctry.model.ContactModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiEndPoints {

    /**
     * This method will return a list of contacts
     * from server
     *
     * @return List of {@link: anikdas012.anikdas.tk.offlinesynctry.model.ContactModel} object
     */
    @Headers("Content-Type: application/json")
    @GET("/api/offlineSync/")
    fun getContacts(): Call<List<ContactModel>>

    /**
     * This method will add a new contact
     * to server
     *
     * @param contact: {@link: anikdas012.anikdas.tk.offlinesynctry.model.ContactModel} object
     * @return {@link: anikdas012.anikdas.tk.offlinesynctry.model.ContactModel} object
     */
    @Headers("Content-Type: application/json")
    @POST("/api/offlineSync/")
    fun addContact(@Body contact: ContactModel): Call<ContactModel>
}