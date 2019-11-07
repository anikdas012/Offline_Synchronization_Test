package anikdas012.anikdas.tk.offlinesynctry.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactDAO {
    /**
     * This method will add new contact to local database.
     * If conflict happens it will abort operation
     *
     * @param contact: Contact object
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createNewContact(contact: Contact)

    /**
     * This method will delet all entries from local database
     */
    @Query("DELETE FROM Contacts")
    fun deleteAll()

    /**
     * This method will return all contacts from local database
     *
     * @return Observable list of contact objects
     */
    @Query("SELECT * from Contacts")
    fun getAll(): LiveData<List<Contact>>

    /**
     * This method will return all unsynced contacts from
     * local database
     *
     * @return List of contact objects
     */
    @Query("SELECT * from Contacts WHERE status = :syncStatus")
    fun getAllUnsynced(syncStatus: Int): List<Contact>

    /**
     * This method will update sysnc status of all
     * unsynced contacts in local database
     *
     * @param phoneNumber: Phone number of a contact
     * @param syncStatus: Synchronization status
     */
    @Query("UPDATE Contacts SET status = :syncStatus WHERE Phone_Number = :phoneNumber")
    suspend fun updateUnsynced(phoneNumber: String, syncStatus: Int)

    /**
     * This method will return a contact from database using phone number
     * if exists
     *
     * @param phoneNumber: Phone number of a contact
     * @return Contact object or null
     */
    @Query("SELECT * from Contacts WHERE Phone_Number = :phoneNumber")
    fun getContact(phoneNumber: String): Contact?
}