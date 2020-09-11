package com.android.petprog.gadsleaderboard.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.android.petprog.gadsleaderboard.R
import com.google.android.material.tabs.TabLayout

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var submitButton: Button

    private lateinit var toolbar: Toolbar;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderbaord)

        toolbar = findViewById(R.id.tool_bar_leaderboard)
        setSupportActionBar(toolbar)

        submitButton = findViewById(R.id.submit_button)

        submitButton.setOnClickListener {
            startActivity(Intent(this, SubmitActivity::class.java))
        }

        val viewPagerAdapter =
            ViewPagerAdapter(
                this,
                supportFragmentManager
            )
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = viewPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)


    }
}