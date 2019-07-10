package anikdas012.anikdas.tk.offlinesynctry.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun createNewContact(contact: Contact)

    @Query("DELETE FROM Contacts")
    fun deleteAll()

    @Query("SELECT * from Contacts ORDER BY id ASC")
    fun getAll(): List<Contact>

    @Query("SELECT * from Contacts WHERE status = 1 ORDER BY id ASC")
    fun getAllUnsynced(): List<Contact>
}