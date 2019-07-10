package anikdas012.anikdas.tk.offlinesynctry.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Contact(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo() var name: String,
    var number: String
)