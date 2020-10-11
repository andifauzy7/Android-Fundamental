package com.example.submissiontiga.db

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTHORITY = "com.example.submissiontiga"
    const val SCHEME = "content"
    internal class UserColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "user_table"
            const val USERNAME = "username"
            const val AVATAR = "avatar"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}