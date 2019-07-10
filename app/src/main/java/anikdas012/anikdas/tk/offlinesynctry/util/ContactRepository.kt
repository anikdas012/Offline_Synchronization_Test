package anikdas012.anikdas.tk.offlinesynctry.util

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import anikdas012.anikdas.tk.offlinesynctry.database.Contact
import anikdas012.anikdas.tk.offlinesynctry.database.ContactDAO

class ContactRepository(private val contactDao: ContactDAO) {

    val allContacts: LiveData<List<Contact>> = contactDao.getAll()

    @WorkerThread
    suspend fun createContact(contact: Contact) {
        contactDao.createNewContact(contact)
    }
}