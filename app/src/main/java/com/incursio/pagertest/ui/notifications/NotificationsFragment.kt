package com.incursio.pagertest.ui.notifications

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
import java.lang.ClassCastException

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var notificationsListener: NotificationsListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        notificationsListener.onStartNotifications()

        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(this, Observer {
            textView.text = it
        })

        return root
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is NotificationsListener) {
            notificationsListener = context
        } else {
            throw ClassCastException("${context.toString()} must implement ${NotificationsListener::class.java}")
        }
    }

    fun setCounterValue(value: Int) {
        Log.d("NotificationsFragment", "Counter set to $value")
        notificationsViewModel.updateText("Counter: $value")
    }
}