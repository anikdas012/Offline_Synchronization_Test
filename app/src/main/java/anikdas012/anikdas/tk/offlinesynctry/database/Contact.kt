package anikdas012.anikdas.tk.offlinesynctry.database

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Contact(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "Name") var name: String,
    @ColumnInfo(name = "Phone_Number") var number: String
)