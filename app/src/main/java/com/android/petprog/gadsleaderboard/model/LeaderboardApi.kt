/**
 * Created by Taiwo Farinu on 11-Sep-20
 */

package com.android.petprog.gadsleaderboard.model

import retrofit2.Call
import retrofit2.http.GET

interface LeaderboardApi {

    @GET("/api/hours")
    fun getLearningLeaders(): Call<List<LearningLeader>>

    @GET("/api/skilliq")
    fun getSkillIQLeaders(): Call<List<SkillIQ>>
}