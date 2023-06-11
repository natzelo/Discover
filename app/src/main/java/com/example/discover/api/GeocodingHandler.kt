package com.example.discover.api

import android.app.Activity
import android.location.Address
import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import java.io.IOException

class GeocodingHandler {
    companion object {
        fun coordinateProvider(activity: Activity, location: String ): LatLng {
            var addresses = listOf<Address>()

            val geocoder = Geocoder(activity)

            try {
                addresses = geocoder.getFromLocationName(location, 1) as List<Address>
            } catch (e: IOException) {
                e.printStackTrace()
            }

            val address: Address = addresses.get(0)
            return LatLng(address.latitude, address.longitude)
        }
    }

}