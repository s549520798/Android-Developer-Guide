package com.lazylee.apiguidedemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lazylee.apiguidedemo.activities.ActivitiesFragment;
import com.lazylee.apiguidedemo.backgroundtasks.BackgroundTasksFragment;
import com.lazylee.apiguidedemo.connectivity.ConnectivityFragment;
import com.lazylee.apiguidedemo.images_graphics.ImagesAndGraphicsFragment;
import com.lazylee.apiguidedemo.intents.IntentsFragment;
import com.lazylee.apiguidedemo.recyclerview.RecyclerFragment;
import com.lazylee.apiguidedemo.ui_navigation.UiNavFragment;

import static com.lazylee.apiguidedemo.FragmentHelper.getCurrentVisibleFragment;
import static com.lazylee.apiguidedemo.FragmentHelper.getFragmentByTag;

public class GuideActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ActionBar actionBar;

    public static final int TYPE_ACTIVITIES = 0;
    public static final int TYPE_INTENTS = 1;
    public static final int TYPE_UI_NAVIGATION = 2;
    public static final int TYPE_IMAGES_GRAPHICS = 5;
    public static final int TYPE_BACKGROUND_TASKS = 7;
    public static final int TYPE_CONNECTIVITY = 13;
    public static final int TYPE_RECYCLER = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setEnterFragment();
    }

    private void setEnterFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_view, ActivitiesFragment.newInstance(), ActivitiesFragment.TAG)
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.guide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_activities:
                onFragmentChanges(getSupportFragmentManager(), TYPE_ACTIVITIES);
                actionBar.setTitle("Activities");
                break;
            case R.id.nav_intents:
                onFragmentChanges(getSupportFragmentManager(), TYPE_INTENTS);
                actionBar.setTitle("intents and intent filter");
                break;
            case R.id.nav_user_interface:
                onFragmentChanges(getSupportFragmentManager(), TYPE_UI_NAVIGATION);
                actionBar.setTitle("UI & Navigation");
                break;
            case R.id.nav_image:
                onFragmentChanges(getSupportFragmentManager(), TYPE_IMAGES_GRAPHICS);
                actionBar.setTitle("Images & Graphics");
                break;
            case R.id.nav_background_tasks:
                onFragmentChanges(getSupportFragmentManager(), TYPE_BACKGROUND_TASKS);
                actionBar.setTitle(R.string.guide_background_tasks);
                break;
            case R.id.nav_connectivity:
                onFragmentChanges(getSupportFragmentManager(), TYPE_CONNECTIVITY);
                actionBar.setTitle("CONNECTIVITY");
                break;
            case R.id.nav_recycler:
                onFragmentChanges(getSupportFragmentManager(), TYPE_RECYCLER);
                actionBar.setTitle(R.string.guide_recycler);
                break;
            default:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onFragmentChanges(FragmentManager manager, int type) {
        Fragment currentFragment = getCurrentVisibleFragment(manager);
        ActivitiesFragment activitiesFragment = (ActivitiesFragment) getFragmentByTag(
                manager,
                ActivitiesFragment.TAG);
        IntentsFragment intentsFragment = (IntentsFragment) getFragmentByTag(
                manager,
                IntentsFragment.TAG);
        UiNavFragment uiNavFragment = (UiNavFragment) getFragmentByTag(
                manager,
                UiNavFragment.TAG);
        ImagesAndGraphicsFragment imagesAndGraphicsFragment = (ImagesAndGraphicsFragment) getFragmentByTag(
                manager,
                ImagesAndGraphicsFragment.TAG);
        BackgroundTasksFragment backgroundTasksFragment = (BackgroundTasksFragment) getFragmentByTag(
                manager,
                BackgroundTasksFragment.TAG);
        ConnectivityFragment connectivityFragment = (ConnectivityFragment) getFragmentByTag(
                manager,
                ConnectivityFragment.TAG);
        RecyclerFragment recyclerFragment = (RecyclerFragment) getFragmentByTag(
                manager,
                RecyclerFragment.TAG);
        switch (type) {
            case TYPE_ACTIVITIES:
                if (activitiesFragment == null) {
                    addAndHideFragment(manager, ActivitiesFragment.newInstance(), currentFragment);
                } else {
                    showAndHideFragment(manager, activitiesFragment, currentFragment);
                }
                break;
            case TYPE_INTENTS:
                if (intentsFragment == null) {
                    addAndHideFragment(manager, IntentsFragment.newInstance(), currentFragment);
                } else {
                    showAndHideFragment(manager, intentsFragment, currentFragment);
                }

                break;
            case TYPE_UI_NAVIGATION:
                if (uiNavFragment == null) {
                    addAndHideFragment(manager, UiNavFragment.newInstance(), currentFragment);
                } else {
                    showAndHideFragment(manager, uiNavFragment, currentFragment);
                }
                break;
            case TYPE_CONNECTIVITY:
                if (connectivityFragment == null) {
                    addAndHideFragment(manager, ConnectivityFragment.newInstance(), currentFragment);
                } else {
                    showAndHideFragment(manager, connectivityFragment, currentFragment);
                }

                break;
            case TYPE_IMAGES_GRAPHICS:
                if (imagesAndGraphicsFragment == null) {
                    addAndHideFragment(manager, ImagesAndGraphicsFragment.newInstance(), currentFragment);
                } else {
                    showAndHideFragment(manager, imagesAndGraphicsFragment, currentFragment);
                }
                break;
            case TYPE_BACKGROUND_TASKS:
                if (backgroundTasksFragment == null) {
                    addAndHideFragment(manager, BackgroundTasksFragment.newInstance(), currentFragment);
                } else {
                    showAndHideFragment(manager, backgroundTasksFragment, currentFragment);
                }
                break;
            case TYPE_RECYCLER:
                if (recyclerFragment == null) {
                    addAndHideFragment(manager, RecyclerFragment.newInstance(), currentFragment);
                } else {
                    showAndHideFragment(manager, recyclerFragment, currentFragment);
                }
                break;
        }
    }

    public void addAndHideFragment(FragmentManager manager, Fragment toAdd, Fragment toHide) {
        toHide.onHiddenChanged(true);
        manager.beginTransaction()
                .hide(toHide)
                .add(R.id.container_view, toAdd, toAdd.getClass().getSimpleName())
                .commit();
        toAdd.onHiddenChanged(false);
    }

    public void showAndHideFragment(FragmentManager manager, Fragment toShow, Fragment toHide) {
        toHide.onHiddenChanged(true);
        manager.beginTransaction()
                .hide(toHide)
                .show(toShow)
                .commit();
        toShow.onHiddenChanged(false);
    }

}
