package com.example.cropdigital.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemsResponse(
    @Json(name = "_id")
    val id: String,
    @Json(name = "index")
    val index: Int,
    @Json(name = "parcel")
    val parcel: String,
    @Json(name = "taskType")
    val taskType: String,
    @Json(name = "comment")
    val comment: String,
    @Json(name = "createdAt")
    val createdAt: Long,
    @Json(name = "updatedAt")
    val updatedAt: Long,
    @Json(name = "__v")
    val v: Int
) : Parcelable