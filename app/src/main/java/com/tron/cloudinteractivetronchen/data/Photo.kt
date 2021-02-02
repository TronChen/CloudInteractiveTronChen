package com.tron.cloudinteractivetronchen.data

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Photo(
    var albumId: Int? = null,
    var id: Int? = null,
    var title: String? = null,
    var url: String? = null,
    var thumbnailUrl: String? = null
):Parcelable {
    @IgnoredOnParcel
    var bitmap: Bitmap? = null
}