package anikdas012.anikdas.tk.offlinesynctry.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import anikdas012.anikdas.tk.offlinesynctry.database.Contact
import anikdas012.anikdas.tk.offlinesynctry.database.ContactDatabase
import anikdas012.anikdas.tk.offlinesynctry.model.ContactModel
import anikdas012.anikdas.tk.offlinesynctry.util.AppUtil
import anikdas012.anikdas.tk.offlinesynctry.util.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ContactRepository
    val allContacts: LiveData<List<Contact>>

    init {
        val contactDao = ContactDatabase.getDatabase(application, viewModelScope).contactDao()
        repository = ContactRepository(contactDao)
        allContacts = repository.allContacts
    }

    fun createContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repository.createContact(contact)
    }


    fun addContact(name: String, number: String) {
        if (AppUtil.isNetworkConnected(getApplication())) {
            val contact = ContactModel(name, number, null)
        }
    }
}