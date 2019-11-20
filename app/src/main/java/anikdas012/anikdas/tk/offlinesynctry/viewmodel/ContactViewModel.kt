package anikdas012.anikdas.tk.offlinesynctry.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.work.*
import anikdas012.anikdas.tk.offlinesynctry.database.Contact
import anikdas012.anikdas.tk.offlinesynctry.database.ContactDatabase
import anikdas012.anikdas.tk.offlinesynctry.model.ContactModel
import anikdas012.anikdas.tk.offlinesynctry.network.retrofit.ApiClient
import anikdas012.anikdas.tk.offlinesynctry.synchronization.SyncWorker
import anikdas012.anikdas.tk.offlinesynctry.util.AppUtil
import anikdas012.anikdas.tk.offlinesynctry.util.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class ContactViewModel(application: Application): AndroidViewModel(application) {

    private val LOG_TAG = "OFFLINE_Contact_ViewModel"

    private val repository: ContactRepository
    val allContacts: LiveData<List<Contact>>
    private lateinit var workManager: WorkManager

    /**
     * Initializing the class with some class properties
     */
    init {
        Log.d(LOG_TAG, "init")
        val contactDao = ContactDatabase.getDatabase(application).contactDao()
        repository = ContactRepository(contactDao)
        allContacts = repository.allContacts
        workManager = WorkManager.getInstance(application)
    }

    /**
     * This method will add a new contact in database
     * using a new thread
     *
     * @param contact: {@link: anikdas012.anikdas.tk.offlinesynctry.database.Contact} object
     */
    fun createContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        Log.d(LOG_TAG, "createContact")
        repository.createContact(contact)
    }


    /**
     * This method will add a new contact in both server and
     * database depending on network availability
     *
     * @param name: Name of the contact
     * @param number: Phone number of the contact
     */
    fun addContact(name: String, number: String) {
        Log.d(LOG_TAG, "addContact")
        if (AppUtil.isNetworkConnected(getApplication())) {
            syncContact(name, number)
        } else {
            createContact(Contact(name, number, AppUtil.STATUS_UNSYNCED))
        }
    }


    /**
     * This method will update a specific contact in database
     *
     * @param number: Phone number of the contact
     * @param syncStatus: Synchronization status
     */
    fun updateContact(number: String, syncStatus: Int) = viewModelScope.launch(Dispatchers.IO) {
        Log.d(LOG_TAG, "updateContact")
        repository.updateContact(number, syncStatus)
    }


    /**
     * This method will return a specific contact from
     * database if exists
     *
     * @param number: Phone number of the contact
     */
    fun getLocalContact(number: String) = repository.getContactFromDatabase(number)


    /**
     * This method will sync all unsync contacts
     * and update their status
     */
    fun syncUnsyncContacts() {
        Log.d(LOG_TAG, "syncUnsyncContacts")
        val unSyncContacts = repository.getUnSyncedContacts()
        /*for (contact in unSyncContacts) {
            syncContact(contact.name, contact.number)
        }*/

        println("*********////******* line 1")
        val constant: Constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        println("*********////******* line 2")
        val periodicWorkRequest = PeriodicWorkRequestBuilder<SyncWorker>(15, TimeUnit.MINUTES, 5, TimeUnit.MINUTES).setConstraints(constant).build()
        println("*********////******* line 3")
        workManager.enqueueUniquePeriodicWork("Sync work", ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequest)
        println("*********////******* line 4")

    }


    /**
     * This method will sync contact between
     * local database and server
     *
     * @param name: Name of the contact
     * @param number: Number of the contact
     */
    fun syncContact(name: String, number: String) {
        Log.d(LOG_TAG, "syncContact")
        val contact = ContactModel(name, number, null)
        val addContact = ApiClient.getApi().addContact(contact)
        addContact.enqueue(object : Callback<ContactModel> {
            override fun onResponse(call: Call<ContactModel>, response: Response<ContactModel>) {
                Log.d(LOG_TAG, "syncContact_onResponse")
                println("***///*** ${response.isSuccessful}  ;  ${response.body()}")
                if (response.isSuccessful and (response.body()?.id != null)) {
                    if (getLocalContact(number) != null) {
                        updateContact(number, AppUtil.STATUS_SYNCED)
                    } else {
                        createContact(Contact(name, number, AppUtil.STATUS_SYNCED))
                    }
                } else {
                    createContact(Contact(name, number, AppUtil.STATUS_UNSYNCED))
                }
            }

            override fun onFailure(call: Call<ContactModel>, t: Throwable) {
                Log.d(LOG_TAG, "syncContact_onFailure ${t.message}")
                createContact(Contact(name, number, AppUtil.STATUS_UNSYNCED))
            }
        })
    }
}