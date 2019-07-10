package anikdas012.anikdas.tk.offlinesynctry.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "Name") val name: String,
    @ColumnInfo(name = "Phone_Number") val number: String,
    val status: Int
)