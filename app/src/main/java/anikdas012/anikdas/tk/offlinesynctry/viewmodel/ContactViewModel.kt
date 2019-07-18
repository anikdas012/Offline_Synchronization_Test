package anikdas012.anikdas.tk.offlinesynctry.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import anikdas012.anikdas.tk.offlinesynctry.database.Contact
import anikdas012.anikdas.tk.offlinesynctry.database.ContactDatabase
import anikdas012.anikdas.tk.offlinesynctry.model.ContactModel
import anikdas012.anikdas.tk.offlinesynctry.network.retrofit.ApiClient
import anikdas012.anikdas.tk.offlinesynctry.util.AppUtil
import anikdas012.anikdas.tk.offlinesynctry.util.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ContactRepository
    val allContacts: LiveData<List<Contact>>

    /**
     * Initializing the class with some class properties
     */
    init {
        val contactDao = ContactDatabase.getDatabase(application, viewModelScope).contactDao()
        repository = ContactRepository(contactDao)
        allContacts = repository.allContacts
    }

    /**
     * This method will add a new contact in database
     * using a new thread
     */
    fun createContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repository.createContact(contact)
    }


    /**
     * This method will add a new contact in both server and
     * database depending on network availability
     */
    fun addContact(name: String, number: String) {
        if (AppUtil.isNetworkConnected(getApplication())) {
            syncContact(name, number)
        }
    }


    /**
     * This method will update a specific contact in database
     */
    fun updateContact(number: String, syncStatus: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateContact(number, syncStatus)
    }


    /**
     * This method will sync contact between
     * local database and server
     */
    fun syncContact(name: String, number: String) {
        val contact = ContactModel(name, number, null)
    }
}