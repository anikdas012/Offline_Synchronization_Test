package anikdas012.anikdas.tk.offlinesynctry.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This class represents table of local database
 */
@Entity(tableName = "Contacts")
data class Contact(
    @ColumnInfo(name = "Name") val name: String,
    @PrimaryKey @ColumnInfo(name = "Phone_Number") val number: String,
    val status: Int
)