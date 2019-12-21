package com.may.catsfacts.services

import com.may.catsfacts.models.ImageItem
import com.may.catsfacts.models.FactItem
import io.reactivex.Observable

interface IAppService {
    fun getImage() : Observable<ImageItem>
    fun getStatement() : Observable<FactItem>
}