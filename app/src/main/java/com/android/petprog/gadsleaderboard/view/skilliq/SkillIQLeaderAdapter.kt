/**
 * Created by Taiwo Farinu on 11-Sep-20
 */

package com.android.petprog.gadsleaderboard.view.skilliq

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.petprog.gadsleaderboard.R
import com.android.petprog.gadsleaderboard.model.SkillIQ

class SkillIQLeaderAdapter(private val learningLeaders: List<SkillIQ>) :
    RecyclerView.Adapter<SkillIQLeaderAdapter.LeaderLearningHolder>() {

    class LeaderLearningHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val learning_name = itemView.findViewById<TextView>(R.id.learner_name_skillIQ)
        val learning_hours = itemView.findViewById<TextView>(R.id.learner_score_skillIQ)
        val learning_country = itemView.findViewById<TextView>(R.id.learner_country_skillIQ)

        fun bind(learning: SkillIQ) {
            learning_name.text = learning.name
            learning_hours.text = learning.hours.toString()
            learning_country.text = learning.country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderLearningHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.skilliq_leaders_itemview, parent, false)
        return LeaderLearningHolder(view)
    }

    override fun getItemCount(): Int = learningLeaders.size

    override fun onBindViewHolder(holder: LeaderLearningHolder, position: Int) {
        val learningLeader = learningLeaders[position]
        holder.bind(learningLeader)

    }


}