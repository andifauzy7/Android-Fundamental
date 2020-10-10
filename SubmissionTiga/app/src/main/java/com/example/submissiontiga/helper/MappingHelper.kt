package com.example.submissiontiga.helper

import android.database.Cursor
import com.example.submissiontiga.db.DatabaseContract
import com.example.submissiontiga.model.User

object MappingHelper {

    fun mapCursorToArrayList(notesCursor: Cursor?): ArrayList<User> {
        val userList = ArrayList<User>()
        notesCursor?.apply {
            while (moveToNext()) {
                val username = getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.USERNAME))
                val avatar = getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.AVATAR))
                userList.add(User(username, avatar))
            }
        }
        return userList
    }
}