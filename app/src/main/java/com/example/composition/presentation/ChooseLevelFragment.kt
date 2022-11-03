package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.composition.R

class ChooseLevelFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_level, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        val buttonTestLevel = view.findViewById<Button>(R.id.btn_test_level)

        buttonTestLevel.setOnClickListener{
            transaction.addToBackStack(null)
            transaction.replace(R.id.fragmentContainerView, GameFragment())
            transaction.commit()
        }




    }
}