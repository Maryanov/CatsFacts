package com.may.catsfacts.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.may.catsfacts.R
import com.may.catsfacts.TheApp
import com.may.catsfacts.mvp.presenters.LowFragmentPresenter
import com.may.catsfacts.mvp.presenters.TopFragmentPresenter
import com.may.catsfacts.ui.fragments.FragmentLow
import com.may.catsfacts.ui.fragments.FragmentTop
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var topPresenter : TopFragmentPresenter
    lateinit var lowPresenter : LowFragmentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        topPresenter = (application as TheApp).container.topPresenter
        lowPresenter = (application as TheApp).container.lowPresenter

        val fragmentManager = supportFragmentManager
        fragmentManager
            .beginTransaction()
            .add(R.id.top_fragment_container, FragmentTop(topPresenter))
            .add(R.id.low_fragment_container, FragmentLow(lowPresenter))
            .commit()

        topPresenter.getFeed()
        lowPresenter.getFeed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_load -> {
                topPresenter.getFeed()
                lowPresenter.getFeed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        topPresenter.detachView()
        lowPresenter.detachView()
        super.onDestroy()
    }
}
