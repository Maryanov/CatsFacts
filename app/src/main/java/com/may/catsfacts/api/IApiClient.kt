package com.may.catsfacts.api

import io.reactivex.Observable
import retrofit2.http.GET

interface IApiClient {

    @GET("meow")
    fun getImage(): Observable<ImageResponse>

    @GET("facts/random")
    fun getStatement(): Observable<FactsResponse>

}
