package com.incursio.pagertest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.incursio.pagertest.ui.dashboard.DashboardFragment
import com.incursio.pagertest.ui.dashboard.DashboardListener
import com.incursio.pagertest.ui.home.HomeFragment
import com.incursio.pagertest.ui.notifications.NotificationsFragment
import com.incursio.pagertest.ui.notifications.NotificationsListener

class TabActivity : AppCompatActivity(), DashboardListener, NotificationsListener {
    // Save in Activity View Model to persist configuration changes
    var dashboardCounter = 0
    var notificationsCounter = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onStartDashboard() {
        Log.d(TabActivity::class.java.toString(), "On start dashboard")
        if (dashboardCounter == 0) {
            dashboardCounter++
        }

        val dashboardFragment =
            supportFragmentManager.fragments[0].childFragmentManager.fragments[0] as? DashboardFragment
        dashboardFragment?.setCounterValue(dashboardCounter)
    }

    override fun onStartNotifications() {
        Log.d(TabActivity::class.java.toString(), "On start notifications")
        if (notificationsCounter == 0) {
            notificationsCounter++
        }

        val notificationsFragment =
            supportFragmentManager.fragments[0].childFragmentManager.fragments[0] as? NotificationsFragment

        notificationsFragment?.setCounterValue(notificationsCounter)
    }
}
