package com.example.discover.views

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.discover.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment


class MapActivity : AppCompatActivity() , OnMapReadyCallback {

    private val tag = "MapActivity"
    private val fineLocation: String = Manifest.permission.ACCESS_FINE_LOCATION
    private val courseLocation: String = Manifest.permission.ACCESS_COARSE_LOCATION
    private val locationRequestCode = 1234

    private var mLocationPermissionsGranted = false
    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        getLocationPermission()
    }

    private fun getLocationPermission() {
        Log.d(tag, "getLocationPermission: getting location permissions")
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                fineLocation
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (ContextCompat.checkSelfPermission(
                    this.applicationContext,
                    courseLocation
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                mLocationPermissionsGranted = true
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    permissions,
                    locationRequestCode
                )
            }
        } else {
            ActivityCompat.requestPermissions(
                this,
                permissions,
                locationRequestCode
            )
        }
    }

    private fun initMap() {
        Log.d(tag, "initMap: initializing map")
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this@MapActivity)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show()
        Log.d(tag, "onMapReady: map is ready")
        mMap = googleMap
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d(tag, "Permission results arrived")
        var mLocationPermissionGranted = false

        when(requestCode) {
            locationRequestCode -> {
                if(grantResults.isNotEmpty()) {
                    for (i in 0..grantResults.size) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false
                            Log.d(tag, "onRequestPermissionsResult: permission failed")
                            return
                        }
                    }
                    Log.d(tag, "onRequestPermissionsResult: permission granted")
                    mLocationPermissionsGranted = true
                    //initialize our map
                    initMap()
                }
            }
        }
    }
}