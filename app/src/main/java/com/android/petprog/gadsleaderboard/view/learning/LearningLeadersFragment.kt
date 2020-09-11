package com.android.petprog.gadsleaderboard.view.learning

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.petprog.gadsleaderboard.R
import com.android.petprog.gadsleaderboard.model.LeaderboardApi
import com.android.petprog.gadsleaderboard.model.LeaderboardApiService
import com.android.petprog.gadsleaderboard.model.LearningLeader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LearningLeadersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LearningLeadersFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var learningLeaderList: List<LearningLeader>;
    private lateinit var adapter: LeaderLearningAdapter
    private lateinit var apiService: LeaderboardApi
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_learning_leaders, container, false)

        recyclerView = view.findViewById(R.id.learning_leaders_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        progressBar = view.findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        apiService = LeaderboardApiService().api.create(LeaderboardApi::class.java)
        val call: Call<List<LearningLeader>> = apiService.getLearningLeaders()
        call.enqueue(object : Callback<List<LearningLeader>> {
            override fun onFailure(call: Call<List<LearningLeader>>, t: Throwable) {

                Log.i("LearningLeaderFragment", "There was a failure")
            }

            override fun onResponse(
                call: Call<List<LearningLeader>>,
                response: Response<List<LearningLeader>>
            ) {
                progressBar.visibility = View.INVISIBLE
                learningLeaderList = response.body()!!
                adapter = LeaderLearningAdapter(learningLeaderList)
                recyclerView.adapter = adapter
            }

        })
        return view;
    }

}