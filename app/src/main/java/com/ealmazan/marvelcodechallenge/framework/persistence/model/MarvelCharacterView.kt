package com.ealmazan.marvelcodechallenge.framework.persistence.model

import android.os.Parcel
import android.os.Parcelable

data class MarvelCharacterView(
    val id: Int,
    val name: String?,
    val description: String?,
    val modified: String?,
    val thumbnail: String?): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(modified)
        parcel.writeString(thumbnail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MarvelCharacterView> {
        override fun createFromParcel(parcel: Parcel): MarvelCharacterView {
            return MarvelCharacterView(parcel)
        }

        override fun newArray(size: Int): Array<MarvelCharacterView?> {
            return arrayOfNulls(size)
        }
    }
}