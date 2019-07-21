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
        val contactDao = ContactDatabase.getDatabase(application).contactDao()
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
        } else {
            createContact(Contact(name, number, AppUtil.STATUS_UNSYNCED))
        }
    }


    /**
     * This method will update a specific contact in database
     */
    fun updateContact(number: String, syncStatus: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateContact(number, syncStatus)
    }


    /**
     * This method will return a specific contact from
     * database if exists
     */
    fun getLocalContact(number: String) = repository.getContactFromDatabase(number)


    /**
     * This method will sync all unsync contacts
     * and update their status
     */
    fun syncUnsyncContacts() {
        val unSyncContacts = repository.getUnSyncedContacts()
        for (contact in unSyncContacts) {
            syncContact(contact.name, contact.number)
        }
    }


    /**
     * This method will sync contact between
     * local database and server
     */
    fun syncContact(name: String, number: String) {
        val contact = ContactModel(name, number, null)
        val addContact = ApiClient.getApi().addContact(contact)
        addContact.enqueue(object : Callback<ContactModel> {
            override fun onResponse(call: Call<ContactModel>, response: Response<ContactModel>) {
                if (response.isSuccessful and (response.body()?.id != null)) {
                    if (getLocalContact(number) != null) {
                        updateContact(number, AppUtil.STATUS_SYNCED)
                    } else {
                        createContact(Contact(name, number, AppUtil.STATUS_UNSYNCED))
                    }
                }
            }

            override fun onFailure(call: Call<ContactModel>, t: Throwable) {
                createContact(Contact(name, number, AppUtil.STATUS_UNSYNCED))
            }
        })
    }
}