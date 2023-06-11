package com.example.discover.utilities

import android.app.Activity
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

class ServiceChecker(private val activity: Activity) {


     fun isServicesOK(): Boolean {
        val available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(activity)

         return if(available == ConnectionResult.SUCCESS) {
             Toast.makeText(activity, "All good!", Toast.LENGTH_SHORT).show()
             true
         } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
             GoogleApiAvailability.getInstance().getErrorDialog(activity, available, 9001)?.show()
             false
         } else {
             Toast.makeText(activity, "App Incompatible", Toast.LENGTH_SHORT).show()
             false
         }
    }
}