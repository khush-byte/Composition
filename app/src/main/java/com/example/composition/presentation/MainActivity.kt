package com.example.composition.presentation

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.composition.R

class MainActivity : AppCompatActivity() {
    private lateinit var player: MediaPlayer
    private var currentPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = ContextCompat.getColor(this, R.color.teal_200)
        player = MediaPlayer.create(this, R.raw.thame)
        player.isLooping = true
        playSoundtrack(true)
    }

    fun playSoundtrack(doPlay: Boolean) {
        if (doPlay) {
            if (!player.isPlaying) {
                player.seekTo(currentPos);
                player.start();
            }
        } else {
            if (player.isPlaying) {
                currentPos = player.currentPosition
                player.pause();
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()
    }
}