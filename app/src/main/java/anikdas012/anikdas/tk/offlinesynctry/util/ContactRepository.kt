package anikdas012.anikdas.tk.offlinesynctry.util

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import anikdas012.anikdas.tk.offlinesynctry.database.Contact
import anikdas012.anikdas.tk.offlinesynctry.database.ContactDAO

class ContactRepository(private val contactDao: ContactDAO) {

    private val LOG_TAG = "Contact_Repository"

    val allContacts: LiveData<List<Contact>> = contactDao.getAll()

    /**
     * This method will add a new contact to
     * database using a new thread
     */
    @WorkerThread
    suspend fun createContact(contact: Contact) {
        Log.d(LOG_TAG, "createContact")
        contactDao.createNewContact(contact)
    }


    /**
     * This method will update an existing contact of
     * database using a new thread
     */
    @WorkerThread
    suspend fun updateContact(number: String, syncStatus: Int) {
        Log.d(LOG_TAG, "updateContact")
        contactDao.updateUnsynced(number, syncStatus)
    }


    /**
     * This method will return all unsynced contacts
     * from database
     */
    fun getUnSyncedContacts(): List<Contact> {
        Log.d(LOG_TAG, "getUnSyncedContacts")
        return contactDao.getAllUnsynced(AppUtil.STATUS_UNSYNCED)
    }


    /**
     * This method will return a specific contact from
     * database if exists
     */
    fun getContactFromDatabase(number: String): Contact? {
        Log.d(LOG_TAG, "getContactFromDatabase")
        return contactDao.getContact(number)
    }
}