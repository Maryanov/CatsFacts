package com.may.catsfacts.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FactsResponse {

    @SerializedName("used")
    @Expose
    val used : Boolean? = null

    @SerializedName("source")
    @Expose
    val source : String? = null

    @SerializedName("type")
    @Expose
    val type : String? = null

    @SerializedName("deleted")
    @Expose
    val deleted : Boolean? = null

    @SerializedName("_id")
    @Expose
    val _id : String? = null

    @SerializedName("user")
    @Expose
    val user : String? = null

    @SerializedName("text")
    @Expose
    val text : String? = null

    @SerializedName("createdAt")
    @Expose
    val createdAt : String? = null

    @SerializedName("updatedAt")
    @Expose
    val updatedAt : String? = null

    @SerializedName("__v")
    @Expose
    val __v : Int? = null
}