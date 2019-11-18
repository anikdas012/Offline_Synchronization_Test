package anikdas012.anikdas.tk.offlinesynctry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import anikdas012.anikdas.tk.offlinesynctry.adapter.ContactListAdapter
import anikdas012.anikdas.tk.offlinesynctry.viewmodel.ContactViewModel

class MainActivity : AppCompatActivity() {

    private val LOG_TAG = "OFFLINE_Main_Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(LOG_TAG, "onCreate")

//        Initializing view components
        val name: AppCompatEditText = findViewById(R.id.name)
        val number: AppCompatEditText = findViewById(R.id.number)
        val submit: AppCompatButton = findViewById(R.id.submit)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val adapter = ContactListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val viewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)

//        Observing changes in contact list received from database
        viewModel.allContacts.observe(this, Observer {contacts ->
            Log.d(LOG_TAG, "allContacts observer")
//            Setting recycler view adapter to contacts received from database
            contacts?.let { adapter.setContacts(contacts) }
        })

        submit.setOnClickListener {
            Log.d(LOG_TAG, "submit onClickListener")
            viewModel.addContact(name.text.toString(), number.text.toString())
//            Clearing edit texts
            name.setText("")
            number.setText("")
        }

//        Creating work request to upload data when network is connected
        viewModel.syncUnsyncContacts()
    }
}
