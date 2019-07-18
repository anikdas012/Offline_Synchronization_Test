package anikdas012.anikdas.tk.offlinesynctry.model

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.Nullable

/**
 * This class is structured to send and receive
 * responses from server
 */
data class ContactModel(
    @SerializedName("name") val name: String,
    @SerializedName("number") val number: String,
    @SerializedName("id") @Nullable val id: Int?
)