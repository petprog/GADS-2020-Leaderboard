/**
 * Created by Taiwo Farinu on 11-Sep-20
 */

package com.android.petprog.gadsleaderboard.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LeaderboardApiService {

    private val baseUrl = "https://gadsapi.herokuapp.com"

    val api = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


}