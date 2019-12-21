package com.may.catsfacts.mvp.views

import androidx.lifecycle.MutableLiveData
import com.may.catsfacts.models.ImageItem
import com.may.catsfacts.utilities.NetworkState

interface ITopView {
    fun showImage(data: MutableLiveData<ImageItem>)
    fun initialLoadState(state: NetworkState)
}