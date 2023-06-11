package com.example.discover.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class MapViewModel: ViewModel() {
    val centralLocation =  MutableLiveData(LatLng(22.0, 77.0))
}