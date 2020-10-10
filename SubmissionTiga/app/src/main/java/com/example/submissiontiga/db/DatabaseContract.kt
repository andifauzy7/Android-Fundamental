package com.example.submissiontiga.db

import android.provider.BaseColumns

internal class DatabaseContract {

    internal class UserColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "user_table"
            const val USERNAME = "username"
            const val AVATAR = "avatar"
        }
    }
}