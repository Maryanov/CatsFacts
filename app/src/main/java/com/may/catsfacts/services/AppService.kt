package com.may.catsfacts.services

import com.may.catsfacts.api.IApiClient
import com.may.catsfacts.mappers.IFactMapper
import com.may.catsfacts.models.FactItem
import com.may.catsfacts.models.ImageItem
import io.reactivex.Observable

class AppService(private val apiClient: IApiClient, private val appMapper: IFactMapper) : IAppService {

    override fun getImage() : Observable<ImageItem> {
        return apiClient.getImage()
            .map { response ->
                response.file?.let { ImageItem(it) }
            }
    }

    override fun getStatement() : Observable<FactItem> {
        return apiClient.getStatement()
            .map { response ->
                appMapper.mapResponseToNews(response)
            }
    }

}