/**
 * Created by Taiwo Farinu on 11-Sep-20
 */

package com.android.petprog.gadsleaderboard.model

import com.google.gson.annotations.SerializedName

data class SkillIQ(
    @SerializedName("name") var name: String = "",
    @SerializedName("hours") var hours: Int,
    @SerializedName("country") var country: String = ""
)