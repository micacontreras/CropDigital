package com.example.cropdigital.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemsRequest(
    @Json(name = "index")
    val index: Int,
    @Json(name = "parcel")
    val parcel: String,
    @Json(name = "taskType")
    val taskType: String,
    @Json(name = "comment")
    val comment: String
) : Parcelable