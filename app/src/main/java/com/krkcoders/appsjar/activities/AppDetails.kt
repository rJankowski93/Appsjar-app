package com.krkcoders.appsjar.activities

import android.os.Bundle
import android.support.v7.widget.AppCompatTextView
import android.widget.Button
import android.widget.RatingBar
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.krkcoders.appsjar.R
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.krkcoders.appsjar.service.PlayerConfig





class AppDetails : YouTubeBaseActivity() {

    var youTubePlayerView: YouTubePlayerView? = null
    var button: Button? = null
    var onInitializedListener: YouTubePlayer.OnInitializedListener? = null
    var ratingBar: RatingBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_details_activity)

        //        button =  findViewById(R.id.you_tube_player_btn) as Button?;
        youTubePlayerView = findViewById(R.id.youtube_player) as YouTubePlayerView?
        ratingBar = findViewById(R.id.rating) as RatingBar?

        (findViewById(R.id.name_app) as AppCompatTextView).text = "Application 1" //TODO data from DB
        (findViewById(R.id.version_app) as AppCompatTextView).text = "Application 1" //TODO data from DB
        ratingBar?.rating = 2.0F //TODO data from DB



        onInitializedListener = object: YouTubePlayer.OnInitializedListener {
            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
            }

            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?, p2: Boolean) {
                youTubePlayer?.loadVideo("3LiubyYpEUk") //TODO data from DB
            }
        }
//        button?.setOnClickListener { youTubePlayerView?.initialize(PlayerConfig.API_KEY, onInitializedListener); }
    }

    override fun onStart() {
        youTubePlayerView?.initialize(PlayerConfig.API_KEY, onInitializedListener)
        super.onStart()
    }
}