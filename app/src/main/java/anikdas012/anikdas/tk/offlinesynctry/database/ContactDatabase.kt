package anikdas012.anikdas.tk.offlinesynctry.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import anikdas012.anikdas.tk.offlinesynctry.util.AppUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase: RoomDatabase() {

    abstract fun contactDao(): ContactDAO

    companion object {

        private val LOG_TAG = "OFFLINE_ContactDatabase"

        @Volatile
        private var databaseInstance: ContactDatabase? = null

        /**
         * This method will return an instance of
         * database in a singleton pattern
         */
        fun getDatabase(context: Context): ContactDatabase {
            Log.d(LOG_TAG, "getDatabase")
            return databaseInstance ?: synchronized(this) {
//                Creating database object
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "Contact_database")
                    .allowMainThreadQueries()
                    .build()
                databaseInstance = instance
                instance
            }
        }
    }


    /**
     * This class will add call back functionality to database.
     * It will delete previous entries and add new entries in
     * database when called
     */
    private class ContactRoomDatabase(private val scope: CoroutineScope): RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            databaseInstance?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.contactDao())
                }
            }
        }

        /**
         * This method will delete previous databse entries and
         * add new entries to database
         */
        suspend fun populateDatabase(contactDAO: ContactDAO) {
            contactDAO.deleteAll()

            var contact = Contact( name="Anik", number = "01933510831", status = AppUtil.STATUS_UNSYNCED)
            contactDAO.createNewContact(contact)
            contact = Contact(name = "Ani", number = "01515687986", status = AppUtil.STATUS_UNSYNCED)
            contactDAO.createNewContact(contact)
        }
    }
}