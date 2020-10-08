package com.example.submissiontiga.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var username: String,
    var avatar: String
) : Parcelable