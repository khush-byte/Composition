package com.example.composition.presentation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.TransitionDrawable
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.composition.R
import com.example.composition.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var player: MediaPlayer
    private var currentPos = 0
    var isMuted = false
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw java.lang.RuntimeException("ActivityMainBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        _binding = null
    }

    fun setRedAlert() {
        val mColors = arrayOf(
            ColorDrawable(
                ContextCompat.getColor(
                    this,
                    android.R.color.holo_red_light
                )
            ), ColorDrawable(Color.WHITE)
        )
        val mTransition = TransitionDrawable(mColors)
        binding.mainField.background = mTransition
        mTransition.startTransition(500)
    }
}