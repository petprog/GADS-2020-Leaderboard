package com.android.petprog.gadsleaderboard.view.skilliq

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.petprog.gadsleaderboard.R
import com.android.petprog.gadsleaderboard.model.LeaderboardApi
import com.android.petprog.gadsleaderboard.model.LeaderboardApiService
import com.android.petprog.gadsleaderboard.model.SkillIQ
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SkillIQLeadersFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var learningLeaderList: List<SkillIQ>;
    private lateinit var adapter: SkillIQLeaderAdapter
    private lateinit var apiService: LeaderboardApi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_learning_leaders, container, false)

        recyclerView = view.findViewById(R.id.learning_leaders_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        apiService = LeaderboardApiService().api.create(LeaderboardApi::class.java)
        val call: Call<List<SkillIQ>> = apiService.getSkillIQLeaders()
        call.enqueue(object : Callback<List<SkillIQ>> {
            override fun onFailure(call: Call<List<SkillIQ>>, t: Throwable) {

                Log.i("LearningLeaderFragment", "There was a failure")
            }

            override fun onResponse(
                call: Call<List<SkillIQ>>,
                response: Response<List<SkillIQ>>
            ) {
                learningLeaderList = response.body()!!
                adapter = SkillIQLeaderAdapter(learningLeaderList)
                recyclerView.adapter = adapter
            }

        })
        return view;
    }

}