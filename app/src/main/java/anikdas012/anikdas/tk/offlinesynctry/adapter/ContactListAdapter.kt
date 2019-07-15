package anikdas012.anikdas.tk.offlinesynctry.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import anikdas012.anikdas.tk.offlinesynctry.R
import anikdas012.anikdas.tk.offlinesynctry.database.Contact

class ContactListAdapter internal constructor(context: Context): RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var contacts = emptyList<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    inner class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: AppCompatTextView = itemView.findViewById(R.id.name_text)
        val number: AppCompatTextView = itemView.findViewById(R.id.number_text)
        val status: AppCompatImageView = itemView.findViewById(R.id.status_image)
    }
}