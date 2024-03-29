package com.incursio.pagertest.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.incursio.pagertest.R

class DashboardFragment : Fragment() {
    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var dashboardListener: DashboardListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        dashboardListener.onStartDashboard()

        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(this, Observer {
            textView.text = it
        })

        return root
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is DashboardListener) {
            dashboardListener = context
        } else {
            throw ClassCastException("${context.toString()} must implement ${DashboardListener::class.java}")
        }
    }

    fun setCounterValue(value: Int) {
        Log.d("DashboardFragment", "Counter set to $value")
        dashboardViewModel.updateText("Counter: $value")
    }
}