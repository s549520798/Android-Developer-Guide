package com.lazylee.apiguidedemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import com.lazylee.apiguidedemo.coretopics.activities.ActivitiesFragment
import com.lazylee.apiguidedemo.coretopics.backgroundtasks.BackgroundTasksFragment
import com.lazylee.apiguidedemo.coretopics.connectivity.ConnectivityFragment
import com.lazylee.apiguidedemo.coretopics.images_graphics.ImagesAndGraphicsFragment
import com.lazylee.apiguidedemo.coretopics.intents.IntentsFragment
import com.lazylee.apiguidedemo.coretopics.recyclerview.RecyclerFragment
import com.lazylee.apiguidedemo.coretopics.touch.TouchInputFragment
import com.lazylee.apiguidedemo.coretopics.ui_navigation.UiNavFragment
import com.lazylee.apiguidedemo.tools.FragmentHelper
import com.lazylee.apiguidedemo.view.InterfaceActivity

class GuideActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var actionBar: ActionBar? = null

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.guide_activity)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        actionBar = supportActionBar
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        setEnterFragment()
    }

    private fun setEnterFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container_view, ActivitiesFragment.newInstance(), ActivitiesFragment.TAG)
                .commit()
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.guide, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_activities -> {
                onFragmentChanges(supportFragmentManager, TYPE_ACTIVITIES)
                actionBar!!.setTitle(R.string.guide_activities)
            }
            R.id.nav_intents -> {
                onFragmentChanges(supportFragmentManager, TYPE_INTENTS)
                actionBar!!.setTitle(R.string.guide_intent)
            }
            R.id.nav_user_interface -> {
                onFragmentChanges(supportFragmentManager, TYPE_UI_NAVIGATION)
                actionBar!!.setTitle(R.string.guide_user_interface)
            }
            R.id.nav_image -> {
                onFragmentChanges(supportFragmentManager, TYPE_IMAGES_GRAPHICS)
                actionBar!!.setTitle(R.string.guide_image)
            }
            R.id.nav_background_tasks -> {
                onFragmentChanges(supportFragmentManager, TYPE_BACKGROUND_TASKS)
                actionBar!!.setTitle(R.string.guide_background_tasks)
            }
            R.id.nav_connectivity -> {
                onFragmentChanges(supportFragmentManager, TYPE_CONNECTIVITY)
                actionBar!!.setTitle(R.string.guide_connectivity)
            }
            R.id.nav_recycler -> {
                onFragmentChanges(supportFragmentManager, TYPE_RECYCLER)
                actionBar!!.setTitle(R.string.guide_recycler)
            }
            R.id.nav_touch_input -> {
                onFragmentChanges(supportFragmentManager, TYPE_TOUCH_INPUT)
                actionBar!!.setTitle(R.string.touch_input)
            }
            R.id.nav_interface -> {
                startActivity(Intent(this, InterfaceActivity::class.java))
            }
            else -> {
            }
        }
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun onFragmentChanges(manager: FragmentManager, type: Int) {
        val currentFragment = FragmentHelper.getCurrentVisibleFragment(manager)
        val activitiesFragment = FragmentHelper.getFragmentByTag(
                manager,
                ActivitiesFragment.Companion.TAG) as ActivitiesFragment?
        val intentsFragment = FragmentHelper.getFragmentByTag(
                manager,
                IntentsFragment.Companion.TAG) as IntentsFragment?
        val uiNavFragment = FragmentHelper.getFragmentByTag(
                manager,
                UiNavFragment.Companion.TAG) as UiNavFragment?
        val imagesAndGraphicsFragment = FragmentHelper.getFragmentByTag(
                manager,
                ImagesAndGraphicsFragment.Companion.TAG) as ImagesAndGraphicsFragment?
        val backgroundTasksFragment = FragmentHelper.getFragmentByTag(
                manager,
                BackgroundTasksFragment.Companion.TAG) as BackgroundTasksFragment?
        val connectivityFragment = FragmentHelper.getFragmentByTag(
                manager,
                ConnectivityFragment.Companion.TAG) as ConnectivityFragment?
        val recyclerFragment = FragmentHelper.getFragmentByTag(
                manager,
                RecyclerFragment.Companion.TAG) as RecyclerFragment?
        val touchInputFragment = FragmentHelper.getFragmentByTag(manager,
                TouchInputFragment.Companion.TAG) as TouchInputFragment?
        when (type) {
            TYPE_ACTIVITIES -> if (activitiesFragment == null) {
                addAndHideFragment(manager, ActivitiesFragment.Companion.newInstance(), currentFragment)
            } else {
                showAndHideFragment(manager, activitiesFragment, currentFragment)
            }
            TYPE_INTENTS -> if (intentsFragment == null) {
                addAndHideFragment(manager, IntentsFragment.Companion.newInstance(), currentFragment)
            } else {
                showAndHideFragment(manager, intentsFragment, currentFragment)
            }
            TYPE_UI_NAVIGATION -> if (uiNavFragment == null) {
                addAndHideFragment(manager, UiNavFragment.Companion.newInstance(), currentFragment)
            } else {
                showAndHideFragment(manager, uiNavFragment, currentFragment)
            }
            TYPE_CONNECTIVITY -> if (connectivityFragment == null) {
                addAndHideFragment(manager, ConnectivityFragment.Companion.newInstance(), currentFragment)
            } else {
                showAndHideFragment(manager, connectivityFragment, currentFragment)
            }
            TYPE_IMAGES_GRAPHICS -> if (imagesAndGraphicsFragment == null) {
                addAndHideFragment(manager, ImagesAndGraphicsFragment.Companion.newInstance(), currentFragment)
            } else {
                showAndHideFragment(manager, imagesAndGraphicsFragment, currentFragment)
            }
            TYPE_BACKGROUND_TASKS -> if (backgroundTasksFragment == null) {
                addAndHideFragment(manager, BackgroundTasksFragment.Companion.newInstance(), currentFragment)
            } else {
                showAndHideFragment(manager, backgroundTasksFragment, currentFragment)
            }
            TYPE_RECYCLER -> if (recyclerFragment == null) {
                addAndHideFragment(manager, RecyclerFragment.Companion.newInstance(), currentFragment)
            } else {
                showAndHideFragment(manager, recyclerFragment, currentFragment)
            }
            TYPE_TOUCH_INPUT -> if (touchInputFragment == null) {
                addAndHideFragment(manager, TouchInputFragment.Companion.newInstance(), currentFragment)
            } else {
                showAndHideFragment(manager, touchInputFragment, currentFragment)
            }
            else -> {
            }
        }
    }

    fun addAndHideFragment(manager: FragmentManager, toAdd: Fragment, toHide: Fragment?) {
        toHide!!.onHiddenChanged(true)
        manager.beginTransaction()
                .hide(toHide)
                .add(R.id.container_view, toAdd, toAdd.javaClass.simpleName)
                .commit()
        toAdd.onHiddenChanged(false)
    }

    fun showAndHideFragment(manager: FragmentManager, toShow: Fragment, toHide: Fragment?) {
        toHide!!.onHiddenChanged(true)
        manager.beginTransaction()
                .hide(toHide)
                .show(toShow)
                .commit()
        toShow.onHiddenChanged(false)
    }

    companion object {
        const val TYPE_ACTIVITIES = 0
        const val TYPE_INTENTS = 1
        const val TYPE_UI_NAVIGATION = 2
        const val TYPE_IMAGES_GRAPHICS = 5
        const val TYPE_BACKGROUND_TASKS = 7
        const val TYPE_TOUCH_INPUT = 10
        const val TYPE_CONNECTIVITY = 13
        const val TYPE_RECYCLER = 14
    }
}