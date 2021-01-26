package com.tron.cloudinteractivetronchen.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    var albumId: Int? = null,
    var id: Int? = null,
    var title: String? = null,
    var url: String? = null,
    var thumbnailUrl: String? = null
):Parcelable