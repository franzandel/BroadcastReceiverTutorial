package com.tunaikumobile.broadcastreceivertutorial

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var connectionReceiver: ConnectionReceiver
    lateinit var intentFilter: IntentFilter

    // Since Oreo, we can't register Receiver only in Manifest, unless the intent is WhiteListed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectionReceiver = ConnectionReceiver()
        // Action Name can be customized
//        intentFilter = IntentFilter("com.franz.broadcastreceiver.SOME_ACTION")
        intentFilter = IntentFilter()
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")

        btnSendCustomBroadcast.setOnClickListener {
            triggerCustomIntentFromCurrentApp()
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(connectionReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(connectionReceiver)
    }

    private fun triggerCustomIntentFromCurrentApp() {
        val intent = Intent("com.franz.broadcastreceiver.SOME_ACTION")
        sendBroadcast(intent)
    }
}
