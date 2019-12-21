package com.may.catsfacts.mvp.presenters

import androidx.lifecycle.MutableLiveData
import com.may.catsfacts.models.FactItem
import com.may.catsfacts.models.ImageItem
import com.may.catsfacts.mvp.views.ILowView
import com.may.catsfacts.services.IAppService
import com.may.catsfacts.utilities.NetworkState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LowFragmentPresenter(private val service: IAppService) {

    private var factsLiveData = MutableLiveData<ArrayList<FactItem>>()
    var list : ArrayList<FactItem> = arrayListOf()
    private var disposable: Disposable? = null
    private var compositeDisposable = CompositeDisposable()
    private var view : ILowView? = null

    fun getFeed() {
        var retry: (() -> Any)? = null

        if (disposable!=null) disposable!!.dispose()
        val subscription = service.getStatement()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                disposable = it
                view?.initialLoadState(NetworkState.LOADING)
            }
            .doOnComplete {
                view?.initialLoadState(NetworkState.LOADED)
            }
            .doOnError {
                view?.initialLoadState(NetworkState.error(it.message))
            }
            .subscribe({result ->
                factsLiveData.value?.add(0, result)
                view?.showFacts(factsLiveData)
            }, {
                retry = {
                    getFeed()
                }
            })

        compositeDisposable.add(subscription)
    }

    fun attachView(view : ILowView) {
        this.view = view
        factsLiveData.value = list
    }

    fun detachView() {
        view = null
    }

}