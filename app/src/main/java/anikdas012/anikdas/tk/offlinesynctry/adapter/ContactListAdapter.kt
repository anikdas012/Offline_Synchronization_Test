package anikdas012.anikdas.tk.offlinesynctry.adapter

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import anikdas012.anikdas.tk.offlinesynctry.R
import kotlinx.android.synthetic.main.list_items.view.*

class ContactListAdapter internal constructor(context: Context): RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>(){
    inner class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: AppCompatTextView = itemView.findViewById(R.id.name_text)
        val number: AppCompatTextView = itemView.findViewById(R.id.number_text)
        val status: AppCompatImageView = itemView.findViewById(R.id.status_image)
    }
}