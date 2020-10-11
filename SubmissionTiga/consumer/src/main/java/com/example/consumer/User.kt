package com.example.consumer

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val username: String? = null,
    var avatar: String? = null
) : Parcelable