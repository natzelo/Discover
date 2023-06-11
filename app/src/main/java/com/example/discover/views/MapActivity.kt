package com.example.discover.views

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.discover.R
import com.example.discover.api.GeocodingHandler
import com.example.discover.viewmodels.MapViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapActivity : AppCompatActivity() , OnMapReadyCallback {

    private val tag = "MapActivity"
    private lateinit var mapViewModel: MapViewModel
    private lateinit var mMap: GoogleMap
    private lateinit var btn: Button
    private lateinit var searchBar: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        initMap()

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
        mapViewModel = ViewModelProvider(this).get(MapViewModel::class.java)
        searchBar = findViewById(R.id.search_bar)

        searchBar.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val location: String = searchBar.query.toString()
                    val latlng: LatLng = GeocodingHandler.coordinateProvider(this@MapActivity, location)
                    placeMapMarker(latlng)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean { return false }
            }
        )
        mapViewModel.centralLocation.observe(this) { placeMapMarker(it)}
    }

    private fun placeMapMarker(latLng : LatLng) {
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(latLng))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10F))
    }

}