package com.may.catsfacts.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ImageResponse {
    @SerializedName("file")
    @Expose
    var file: String? = null

}