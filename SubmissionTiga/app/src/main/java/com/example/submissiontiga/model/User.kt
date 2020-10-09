package com.example.submissiontiga.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "avatar") var avatar: String
)