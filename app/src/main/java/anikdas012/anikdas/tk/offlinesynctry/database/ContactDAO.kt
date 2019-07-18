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
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun createNewContact(contact: Contact)

    /**
     * This method will delet all entries from local database
     */
    @Query("DELETE FROM Contacts")
    fun deleteAll()

    /**
     * This method will return all contacts from local database
     */
    @Query("SELECT * from Contacts")
    fun getAll(): LiveData<List<Contact>>

    /**
     * This method will return all unsynced contacts from
     * local database
     */
    @Query("SELECT * from Contacts WHERE status = :syncStatus")
    fun getAllUnsynced(syncStatus: Int): List<Contact>

    /**
     * This method will update sysnc status of all
     * unsynced contacts in local database
     */
    @Query("UPDATE Contacts SET status = :syncStatus WHERE Phone_Number = :phoneNumber")
    suspend fun updateUnsynced(phoneNumber: String, syncStatus: Int)
}