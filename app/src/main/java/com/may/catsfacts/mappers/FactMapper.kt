package com.may.catsfacts.mappers

import com.may.catsfacts.api.FactsResponse
import com.may.catsfacts.extensions.formatDateTimeRus
import com.may.catsfacts.models.FactItem

class FactMapper : IFactMapper {

    override fun mapResponseToNews(source : FactsResponse) : FactItem {
        return source.text?.let { source.createdAt?.let { it1 -> FactItem(it, it1.formatDateTimeRus()) } } ?: FactItem("no statements", "no data")
    }

}