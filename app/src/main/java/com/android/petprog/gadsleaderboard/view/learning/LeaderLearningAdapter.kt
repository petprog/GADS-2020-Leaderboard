/**
 * Created by Taiwo Farinu on 11-Sep-20
 */

package com.android.petprog.gadsleaderboard.view.learning

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.petprog.gadsleaderboard.R
import com.android.petprog.gadsleaderboard.model.LearningLeader

class LeaderLearningAdapter(private val learningLeaders: List<LearningLeader>) :
    RecyclerView.Adapter<LeaderLearningAdapter.LeaderLearningHolder>() {

    class LeaderLearningHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val learning_name = itemView.findViewById<TextView>(R.id.learner_name)
        val learning_hours = itemView.findViewById<TextView>(R.id.learner_score)
        val learning_country = itemView.findViewById<TextView>(R.id.learner_country)
        fun bind(learning: LearningLeader) {
            learning_name.text = learning.name
            learning_hours.text = learning.hours.toString()
            learning_country.text = learning.country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderLearningHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.learning_leaders_itemview, parent, false)
        return LeaderLearningHolder(view)
    }

    override fun getItemCount(): Int = learningLeaders.size

    override fun onBindViewHolder(holder: LeaderLearningHolder, position: Int) {
        val learningLeader = learningLeaders[position]
        holder.bind(learningLeader)

    }


}