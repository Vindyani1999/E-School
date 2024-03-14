package com.example.loginsingupauth

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class VideoPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val videoUrl = intent.getStringExtra("videoUrl")
        val videoFileName = intent.getStringExtra("videoFileName")

        if (videoUrl != null) {
            playVideo(videoUrl, videoFileName)
        }
    }

    private fun playVideo(videoUrl: String, videoFileName: String?) {
        val videoView: VideoView = findViewById(R.id.videoView)


        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(Uri.parse(videoUrl))

        supportActionBar?.title = videoFileName


        videoView.start()
    }
}
