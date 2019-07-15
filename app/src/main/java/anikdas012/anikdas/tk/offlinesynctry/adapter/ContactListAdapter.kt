package anikdas012.anikdas.tk.offlinesynctry.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import anikdas012.anikdas.tk.offlinesynctry.R

class ContactListAdapter internal constructor(context: Context): RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    inner class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: AppCompatTextView = itemView.findViewById(R.id.name_text)
        val number: AppCompatTextView = itemView.findViewById(R.id.number_text)
        val status: AppCompatImageView = itemView.findViewById(R.id.status_image)
    }
}