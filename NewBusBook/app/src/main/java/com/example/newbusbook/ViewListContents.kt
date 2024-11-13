package com.example.newbusbook

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ViewListContents : AppCompatActivity() {

    private lateinit var myDB: DBHelper
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewlistcontents_layout)

        val listView: ListView = findViewById(R.id.listView)
        myDB = DBHelper(this)

        // Populate an ArrayList<String> from the database and then view it
        val theList = ArrayList<String>()
        val data: Cursor = myDB.viewBuses()

        if (data.count == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show()
        } else {
            while (data.moveToNext()) {
                theList.add("ID:   ${data.getString(0)}                             " +
                        "FROM:  ${data.getString(1)}                             " +
                        "To:  ${data.getString(2)}                              " +
                        "Date:  ${data.getString(3)}                              " +
                        "Seats:  ${data.getString(4)}")
                theList.add("")

                val listAdapter: ListAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, theList)
                listView.adapter = listAdapter

                listView.setOnItemClickListener { parent, view, position, id ->
                    // Use position to determine which item was clicked
                    val intent = Intent(view.context, SeatSelection::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
