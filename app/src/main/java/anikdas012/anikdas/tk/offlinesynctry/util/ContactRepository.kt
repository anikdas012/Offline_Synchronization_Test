package anikdas012.anikdas.tk.offlinesynctry.util

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import anikdas012.anikdas.tk.offlinesynctry.database.Contact
import anikdas012.anikdas.tk.offlinesynctry.database.ContactDAO

class ContactRepository(private val contactDao: ContactDAO) {

    val allContacts: LiveData<List<Contact>> = contactDao.getAll()

    /**
     * This method will add a new contact to
     * database using a new thread
     */
    @WorkerThread
    suspend fun createContact(contact: Contact) {
        contactDao.createNewContact(contact)
    }


    /**
     * This method will update an existing contact of
     * database using a new thread
     */
    @WorkerThread
    suspend fun updateContact(contact: Contact, syncStatus: Int) {
        contactDao.updateUnsynced(contact.number, syncStatus)
    }


    /**
     * This method will return all unsynced contacts
     * from database
     */
    fun getUnSyncedContacts(): List<Contact> {
        return contactDao.getAllUnsynced(AppUtil.STATUS_UNSYNCED)
    }
}