package anikdas012.anikdas.tk.offlinesynctry.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import anikdas012.anikdas.tk.offlinesynctry.R
import anikdas012.anikdas.tk.offlinesynctry.database.Contact
import anikdas012.anikdas.tk.offlinesynctry.util.AppUtil

class ContactListAdapter internal constructor(context: Context): RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>(){

    private val LOG_TAG = "OFFLINE_Contact_Adapter"

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var contacts = emptyList<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        Log.d(LOG_TAG, "onCreateViewHolder")
        val itemView = inflater.inflate(R.layout.list_items, parent, false)
        return ContactViewHolder(itemView)
    }

    /**
     * This method will return size of contact list
     *
     * @return Size of contact list
     */
    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        Log.d(LOG_TAG, "onBindViewHolder")
        val currentContact = contacts[position]
        holder.name.text = currentContact.name
        holder.number.text = currentContact.number
        if (currentContact.status == AppUtil.STATUS_SYNCED) {
            holder.status.setImageResource(R.drawable.synced)
        } else {
            holder.status.setImageResource(R.drawable.not_synced)
        }
    }

    /**
     * This method will update contact list
     * and notify changes to adapter
     *
     * @param contacts: List of contact
     */
    internal fun setContacts(contacts: List<Contact>) {
        Log.d(LOG_TAG, "setContacts")
        this.contacts = contacts
        notifyDataSetChanged()
    }


    /**
     * View holder class of recycler view
     */
    inner class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: AppCompatTextView = itemView.findViewById(R.id.name_text)
        val number: AppCompatTextView = itemView.findViewById(R.id.number_text)
        val status: AppCompatImageView = itemView.findViewById(R.id.status_image)
    }
}