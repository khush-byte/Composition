package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.composition.R
import com.example.composition.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    //Use the Binding (link)
    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Set params to binding
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        //Return root as view
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonUnderstand.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            transaction.replace(R.id.fragmentContainerView, ChooseLevelFragment())
            transaction.commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}