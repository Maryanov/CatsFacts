package com.may.catsfacts.di

import com.may.catsfacts.api.ApiFactory
import com.may.catsfacts.api.IApiClient
import com.may.catsfacts.mappers.FactMapper
import com.may.catsfacts.mappers.IFactMapper
import com.may.catsfacts.mvp.presenters.LowFragmentPresenter
import com.may.catsfacts.mvp.presenters.TopFragmentPresenter
import com.may.catsfacts.services.AppService
import com.may.catsfacts.services.IAppService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.kodein.di.newInstance

class Container {

    companion object {
        private const val TYPE_CLIENT_CATS_IMAGE = 0
        private const val TYPE_CLIENT_CATS_FACTS = 1
    }
    private val kodein = Kodein {
        bind<IApiClient>(tag="image_cats") with singleton { ApiFactory().createApiClient(TYPE_CLIENT_CATS_IMAGE) }
        bind<IApiClient>(tag="facts_cats") with singleton { ApiFactory().createApiClient(TYPE_CLIENT_CATS_FACTS) }
        bind<IFactMapper>() with singleton { FactMapper() }
        bind<IAppService>(tag="top_fragment") with singleton { AppService(instance("image_cats"), instance()) }
        bind<IAppService>(tag="low_fragment") with singleton { AppService(instance("facts_cats"), instance()) }
    }

    val topPresenter : TopFragmentPresenter by kodein.newInstance { TopFragmentPresenter(instance("top_fragment")) }
    val lowPresenter : LowFragmentPresenter by kodein.newInstance { LowFragmentPresenter(instance("low_fragment")) }

}