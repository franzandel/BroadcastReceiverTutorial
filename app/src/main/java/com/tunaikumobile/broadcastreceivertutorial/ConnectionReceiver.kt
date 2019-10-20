package com.tunaikumobile.broadcastreceivertutorial

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast


/**
 * Created by Franz Andel on 2019-09-08.
 * Android Engineer
 */

class ConnectionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("API123", "" + intent?.action)

        // Custom Intent
        if (intent?.action.equals("com.franz.broadcastreceiver.SOME_ACTION"))
            Toast.makeText(context, "SOME_ACTION is received", Toast.LENGTH_LONG).show()
        // System Intent
        else {
            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetwork = cm.activeNetworkInfo
            val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
            if (isConnected) {
                try {
                    Toast.makeText(context, "Network is connected", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            } else {
                Toast.makeText(context, "Network is changed or reconnected", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}