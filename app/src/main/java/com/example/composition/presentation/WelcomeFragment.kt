package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.composition.R

class WelcomeFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonStart = view.findViewById<Button>(R.id.start_btn)

        buttonStart.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            transaction.replace(R.id.fragmentContainerView, ChooseLevelFragment())
            transaction.commit()
        }
    }
}