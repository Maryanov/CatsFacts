package com.may.catsfacts.mappers

import com.may.catsfacts.api.FactsResponse
import com.may.catsfacts.models.FactItem

interface IFactMapper {
    fun mapResponseToNews(source : FactsResponse) : FactItem
}