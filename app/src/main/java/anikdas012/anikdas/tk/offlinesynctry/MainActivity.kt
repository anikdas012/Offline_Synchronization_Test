package anikdas012.anikdas.tk.offlinesynctry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import anikdas012.anikdas.tk.offlinesynctry.adapter.ContactListAdapter
import anikdas012.anikdas.tk.offlinesynctry.viewmodel.ContactViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Initializing view components
        val name: AppCompatEditText = findViewById(R.id.name)
        val number: AppCompatEditText = findViewById(R.id.number)
        val submit: AppCompatButton = findViewById(R.id.submit)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val adapter = ContactListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val viewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
    }
}
