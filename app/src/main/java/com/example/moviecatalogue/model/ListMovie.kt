package com.example.moviecatalogue.model

import android.os.Parcel
import android.os.Parcelable

data class ListMovie(
    var pict : Int?,
    var name : String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(pict)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListMovie> {
        override fun createFromParcel(parcel: Parcel): ListMovie {
            return ListMovie(parcel)
        }

        override fun newArray(size: Int): Array<ListMovie?> {
            return arrayOfNulls(size)
        }
    }
}