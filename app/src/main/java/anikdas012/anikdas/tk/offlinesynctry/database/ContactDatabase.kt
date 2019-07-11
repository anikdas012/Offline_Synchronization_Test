package anikdas012.anikdas.tk.offlinesynctry.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Contact::class), version = 1)
abstract class ContactDatabase: RoomDatabase() {

    abstract fun contactDao(): ContactDAO

    companion object {
        @Volatile
        private var databaseInstance: ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase {
            return databaseInstance ?: synchronized(this) {
//                Creating database object
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "Contact_database")
                    .build()
                databaseInstance = instance
                instance
            }
        }
    }
}