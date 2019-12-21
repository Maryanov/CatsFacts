package com.may.catsfacts.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.may.catsfacts.R
import com.may.catsfacts.models.FactItem
import com.may.catsfacts.mvp.presenters.LowFragmentPresenter
import com.may.catsfacts.mvp.views.ILowView
import com.may.catsfacts.utilities.NetworkState
import com.may.catsfacts.ui.adapters.FactsListAdapter
import kotlinx.android.synthetic.main.fragment_low.*

class FragmentLow(lowPresenter: LowFragmentPresenter) : Fragment(R.layout.fragment_low) , ILowView{

    lateinit var listAdapter: FactsListAdapter
    private val presenter = lowPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initialLoadState(state: NetworkState){
        when(state){
            NetworkState.LOADING->lowProgressBar.visibility = View.VISIBLE
            NetworkState.LOADED->lowProgressBar.visibility = View.GONE
        }
        if (state.status.name.startsWith("FAILED")) {
            lowProgressBar.visibility = View.GONE
        }
    }

    override fun showFacts(data: MutableLiveData<ArrayList<FactItem>>) {
        listAdapter = FactsListAdapter(data.value!!)
        statementRecyclerView.adapter = listAdapter
    }
}