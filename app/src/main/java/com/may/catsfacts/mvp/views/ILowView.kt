package com.may.catsfacts.mvp.views

import androidx.lifecycle.MutableLiveData
import com.may.catsfacts.models.FactItem
import com.may.catsfacts.utilities.NetworkState

interface ILowView {
    fun showFacts(data: MutableLiveData<ArrayList<FactItem>>)
    fun initialLoadState(state: NetworkState)
}