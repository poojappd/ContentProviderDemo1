package com.example.contentproviderdemo1

import android.content.ContentValues.TAG
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader

class MainActivity : AppCompatActivity() , LoaderManager.LoaderCallbacks<Cursor>{
    private val fieldNames = arrayOf(ContactsContract.Contacts.DISPLAY_NAME,
        ContactsContract.Contacts.CONTACT_STATUS,

        ContactsContract.Contacts.HAS_PHONE_NUMBER)
    private val searchCondition = ContactsContract.Contacts.DISPLAY_NAME + "= ?"
    private val searchArguments = arrayOf("Test1","Test2")
    private lateinit var displayText:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       displayText = findViewById(R.id.uniqueId)

    }

    override fun onResume() {
        super.onResume()
        LoaderManager.getInstance(this).initLoader(1, null, this)
        Log.e(TAG,"jh${displayText.text}")

        //getting the column names of the database i guess

        //the response object for query() is cursor
//        val cursor = contentResolver.query(
//            ContactsContract.Contacts.CONTENT_URI,   // The content URI of the words table
//            fieldNames,                        // The columns to return for each row
//            null,                   // Selection criteria
//           null,      // Selection criteria
//            null                   // The sort order for the returned rows
//        )
//
//        val orderBy = ContactsContract.Contacts.DISPLAY_NAME
//
//
//
//        when (cursor?.count) {
//            null -> {
//                Log.e(TAG,"null??")
//            }
//            0 -> {
//                displayText.text = "No contacts:("
//                Log.e(TAG,"Empty")
//            }
//            else -> {
//                // Insert code here to do something with the results
//                displayText.text = ""
//                while (cursor.moveToNext()) {
//                    displayText.append("${cursor.getString(0)} , ${cursor.getString(1)}  , ${ cursor.getString(
//                        2)}\n")
//                }
//                //displayText.text = (displayText.toString());
//
//            }
//        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        Log.e(TAG, "onCreateLoader")
        return CursorLoader(this, ContactsContract.Contacts.CONTENT_URI, fieldNames, null, null, null )

    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        Log.e(TAG, "*******\nonLoadFinished/n*******")

        when (data?.count) {
            null -> {
                Log.e(TAG, "null??")
            }
            0 -> {
                Log.e(TAG, "Empty")
            }
            else -> {
                // Insert code here to do something with the results
                while (data.moveToNext()) {
                    displayText.append(
                        "${data.getString(0)} , ${data.getString(1)}  , ${
                            data.getString(
                                2
                            )
                        }\n"
                    )
                }
            }
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        Log.e(TAG, "LOADER RESET")
        TODO("Not yet implemented")
    }

    fun buttonOnClick(){
        LoaderManager.getInstance(this).initLoader(1, null, this)
    }
}