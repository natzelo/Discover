package com.example.discover.api
import com.example.discover.models.PlacesList
import retrofit2.http.GET
import retrofit2.http.Query

interface NearbyPlacesAPI {

    @GET("/maps/api/place/nearbysearch/json")
    suspend fun getNearbyPlaces(@Query("key") key: String,
                                @Query("location") location: String,
                                @Query("radius") radius: Int) : retrofit2.Response<PlacesList>
}