package com.may.catsfacts.mvp.presenters

import androidx.lifecycle.MutableLiveData
import com.may.catsfacts.models.ImageItem
import com.may.catsfacts.mvp.views.ITopView
import com.may.catsfacts.services.IAppService
import com.may.catsfacts.utilities.NetworkState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TopFragmentPresenter(private val service: IAppService) {

    private val newDataProviderLiveData = MutableLiveData<ImageItem>()
    private var disposable: Disposable? = null
    private val compositeDisposable = CompositeDisposable()

    private var view : ITopView? = null

    fun getFeed() {
        var retry: (() -> Any)? = null

        if (disposable!=null) disposable!!.dispose()

        val subscription = service.getImage()
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
                //view?.showImage(result)
                newDataProviderLiveData.value = result
                view?.showImage(newDataProviderLiveData)
            }, {
                retry = {
                    getFeed()
                }
            })

        compositeDisposable.add(subscription)
    }

    fun attachView(view : ITopView) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

}