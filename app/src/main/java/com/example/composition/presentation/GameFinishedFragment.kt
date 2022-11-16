package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {
    private val args by navArgs<GameFinishedFragmentArgs>()
    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw java.lang.RuntimeException("FragmentGameFinishedBinding == null")

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = (activity as MainActivity)
        if (!activity.isMuted) {
            activity.playSoundtrack(true)
        }
        binding.gameResult = args.gameResult
        setupClickListeners()
    }

    /*private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
        //Log.d("MyTestLog", gameResult.toString())
    }*/

    private fun setupClickListeners() {
        //Set onBackPressed logic as retry the game
        /*val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                retryGame()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)*/
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

//    private fun bindViews() {
//        binding.gameResult = args.gameResult
//        //binding.emojiResult.setImageResource(getSmileResId())
////        with(binding) {
////            emojiResult.setImageResource(getSmileResId())
////            tvRequiredAnswers.text = String.format(
////                getString(R.string.required_score), args.gameResult.gameSettings.minCountOfRightAnswers
////            )
////            tvScoreAnswers.text = String.format(
////                getString(R.string.score_answers), args.gameResult.countOfRightAnswers
////            )
////            tvRequiredPercentage.text = String.format(
////                getString(R.string.required_percentage),
////                args.gameResult.gameSettings.minPercentOgRightAnswers
////            )
////            tvScorePercentage.text = String.format(
////                getString(R.string.score_percentage), getPercentOfRightAnswers()
////            )
////        }
//    }

//    private fun getSmileResId(): Int {
//        return if (args.gameResult.winner) {
//            R.drawable.ic_smile
//        } else {
//            R.drawable.ic_sad
//        }
//    }

    private fun retryGame() {
        /*requireActivity().supportFragmentManager.popBackStack(
            GameFragment.NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )*/
        findNavController().popBackStack()
    }

//    private fun getPercentOfRightAnswers() = with(args.gameResult) {
//        if (countOfQuestions == 0) {
//            0
//        } else {
//            args.gameResult.percentOfRightAnswers
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //Factory method for set level as param to fragment
    /*companion object {
        const val KEY_GAME_RESULT = "game_result"
        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }*/
}