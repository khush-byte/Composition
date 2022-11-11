package com.example.composition.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        (activity as MainActivity?)?.playSoundtrack(true)
        //Apply setOnClickListener over buttons
        with(binding) {
            btnTestLevel.setOnClickListener { launchGameFragment(Level.TEST) }
            btnEasyLevel.setOnClickListener { launchGameFragment(Level.EASY) }
            btnNormalLevel.setOnClickListener { launchGameFragment(Level.NORMAL) }
            btnHardLevel.setOnClickListener { launchGameFragment(Level.HARD) }
        }
    }

    private fun launchGameFragment(level: Level) {
        //Log.d("MyTestLog", level.name)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val NAME = "ChooseLevelFragment"
        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }
}