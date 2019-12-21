package com.may.catsfacts.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.may.catsfacts.R
import com.may.catsfacts.mvp.presenters.TopFragmentPresenter
import com.may.catsfacts.mvp.views.ITopView
import com.may.catsfacts.utilities.NetworkState
import kotlinx.android.synthetic.main.fragment_top.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import com.may.catsfacts.models.ImageItem
import com.may.catsfacts.utilities.GlideApp

class FragmentTop(topPresenter: TopFragmentPresenter) : Fragment(R.layout.fragment_top), ITopView {

    private var presenter = topPresenter
    private val glideRequests by lazy { GlideApp.with(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initialLoadState(state: NetworkState){
        when(state){
            NetworkState.LOADING->topProgressBar.visibility = View.VISIBLE
            NetworkState.LOADED->topProgressBar.visibility = View.GONE
        }
        if (state.status.name.startsWith("FAILED")) {
            topProgressBar.visibility = View.GONE
            onShowDialog()
        }
    }

    private fun onShowDialog() {

        val builder = AlertDialog.Builder(activity!!)
        builder.setTitle("Внимание!")
            .setMessage("Проверьте инретнет соединение!")
            .setCancelable(false)
            .setNegativeButton("Мяу"
            ) { dialog, _ -> dialog.cancel() }
        val alert = builder.create()
        alert.show()
    }

    override fun showImage(data: MutableLiveData<ImageItem>) {
        glideRequests.load(data.value!!.url)
            .centerCrop()
            .placeholder(R.drawable.icon_lucky_cat)
            .into(imageView)
    }
}