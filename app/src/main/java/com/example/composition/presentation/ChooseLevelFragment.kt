package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.composition.R
import com.example.composition.databinding.FragmentChooseLevelBinding
import com.example.composition.domain.entity.Level

class ChooseLevelFragment : Fragment() {
    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw java.lang.RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = (activity as MainActivity)
        with(binding) {
            btnTestLevel.setOnClickListener { launchGameFragment(Level.TEST) }
            btnEasyLevel.setOnClickListener { launchGameFragment(Level.EASY) }
            btnNormalLevel.setOnClickListener { launchGameFragment(Level.NORMAL) }
            btnHardLevel.setOnClickListener { launchGameFragment(Level.HARD) }
            if (activity.isMuted) {
                btnSound.setImageResource(R.drawable.audio_off)
            } else {
                btnSound.setImageResource(R.drawable.audio_on)
                activity.playSoundtrack(true)
            }
            btnSound.setOnClickListener {
                if (!activity.isMuted) {
                    btnSound.setImageResource(R.drawable.audio_off)
                    activity.playSoundtrack(false)
                    activity.isMuted = true
                } else {
                    btnSound.setImageResource(R.drawable.audio_on)
                    activity.playSoundtrack(true)
                    activity.isMuted = false
                }
            }
        }
    }

    private fun launchGameFragment(level: Level) {
        findNavController().navigate(
            ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(level)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}