package com.example.submissiontiga.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import com.example.submissiontiga.db.DatabaseContract.UserColumns.Companion.CONTENT_URI
import com.example.submissiontiga.db.UserHelper

class UserProvider : ContentProvider() {

    companion object {
        private lateinit var userHelper: UserHelper
    }

    override fun onCreate(): Boolean {
        userHelper = UserHelper.getInstance(context as Context)
        userHelper.open()
        return true
    }
    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        val cursor: Cursor?
        cursor = userHelper.queryAll()
        context?.contentResolver?.notifyChange(CONTENT_URI, null)
        return cursor
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        userHelper.insert(values)
        context?.contentResolver?.notifyChange(CONTENT_URI, null)
        return Uri.parse("$CONTENT_URI")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }
}
