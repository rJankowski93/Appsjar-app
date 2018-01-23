package com.krkcoders.appsjar.activities

import android.content.Intent
import android.graphics.BitmapFactory
import android.opengl.Visibility
import android.os.Bundle
import android.os.Environment
import android.support.v4.content.FileProvider
import android.support.v7.widget.AppCompatTextView
import android.util.Base64
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.krkcoders.appsjar.R
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.krkcoders.appsjar.BuildConfig
import com.krkcoders.appsjar.models.App
import com.krkcoders.appsjar.service.PlayerConfig
import io.realm.Realm
import java.io.File


class AppDetails : YouTubeBaseActivity() {

    var youTubePlayerView: YouTubePlayerView? = null
    //var button: Button? = null
    var imageView: ImageView? = null
    var onInitializedListener: YouTubePlayer.OnInitializedListener? = null
    var ratingBar: RatingBar? = null
    var downloadBtn: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_details_activity)
        var game =  getGame(intent.getStringExtra("gameId"))

        youTubePlayerView = findViewById(R.id.youtube_player) as YouTubePlayerView?
        ratingBar = findViewById(R.id.rating) as RatingBar?
        imageView = findViewById(R.id.imageView) as ImageView?
        val decodedString = Base64.decode(game!!.getImg(), Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        imageView!!.setImageBitmap(decodedByte)

        (findViewById(R.id.name_app) as AppCompatTextView).text = game!!.name
        (findViewById(R.id.version_app) as AppCompatTextView).text = game.appVersion
        ratingBar?.rating = game.rating.toFloat()

        onInitializedListener = object: YouTubePlayer.OnInitializedListener {
            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
            }

            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?, p2: Boolean) {
                youTubePlayer?.loadVideo(game.youtubeId)
            }
        }

        downloadBtn = findViewById(R.id.downloadBtn) as Button?
        if(isAppInstalled(game.name)) {
            downloadBtn?.text = "Check update"
        }

        downloadBtn?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View) {
                if(isAppInstalled(game.name)){
                    //delete this
                    val intent = Intent(Intent.ACTION_VIEW)
                    var file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + "/ColorGame.apk")
                    val appURI = FileProvider.getUriForFile(this@AppDetails,
                            BuildConfig.APPLICATION_ID + ".provider",file)
                    intent.setDataAndType( appURI, "application/vnd.android.package-archive")
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    startActivity(intent)
                }else{
                    //todo download app and put in download directory
                    val intent = Intent(Intent.ACTION_VIEW)
                    var file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + "/ColorGame.apk")
                    val appURI = FileProvider.getUriForFile(this@AppDetails,
                            BuildConfig.APPLICATION_ID + ".provider",file)
                    intent.setDataAndType( appURI, "application/vnd.android.package-archive")
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    startActivity(intent)
                }
            }
        })


        var runButton =findViewById(R.id.runButton) as Button?
        if(!isAppInstalled(game.name)) {
            runButton?.visibility = View.INVISIBLE
        }
//        else{
//            //val launchIntent = packageManager.getLaunchIntentForPackage("com.package.ballgame")
//            val launchIntent = packageManager.getLaunchIntentForPackage(getPackageGame(game.name))
//            if (launchIntent != null) {
//                startActivity(launchIntent)//null pointer check in case package name was not found
//            }
//        }

        runButton?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View) {
                val launchIntent = packageManager.getLaunchIntentForPackage(getPackageGame(game.name))
            if (launchIntent != null) {
                startActivity(launchIntent)//null pointer check in case package name was not found
            }



            }})


    }

    override fun onStart() {
        youTubePlayerView?.initialize(PlayerConfig.API_KEY, onInitializedListener)
        super.onStart()
    }

    private fun getGame(gameId: String): App? {
        val realm = Realm.getDefaultInstance()
        return realm.where(App::class.java).equalTo("id", gameId).findFirst()
    }

    private fun isAppInstalled(apkName: String): Boolean {

        if(apkName.equals("App5")){ return true}

        val pm = getPackageManager()
        val infoList = pm.getInstalledApplications(0)
        for (applicationInfo in infoList) {
            if(applicationInfo.processName!=null && applicationInfo.processName.toString().contains(apkName,true)){
                return true
            }
        }
        return false
    }

    private fun getPackageGame(apkName: String):String{

        val pm = getPackageManager()
        val infoList = pm.getInstalledApplications(0)
        for (applicationInfo in infoList) {
            if(applicationInfo.processName!=null && applicationInfo.processName.toString().contains(apkName,true)){
                return applicationInfo.packageName
            }
        }
        return ""
    }
}