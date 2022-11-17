package com.example.composition.presentation

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.databinding.FragmentGameBinding
import com.example.composition.domain.entity.GameResult
import kotlin.random.Random


class GameFragment : Fragment() {
    private val args by navArgs<GameFragmentArgs>()
    private var activity: MainActivity? = null
    private val viewModelFactory by lazy {
        GameViewModelFactory(args.level, requireActivity().application)
    }
    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        )[GameViewModel::class.java]
    }
    /*private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.tvOption1)
            add(binding.tvOption2)
            add(binding.tvOption3)
            add(binding.tvOption4)
            add(binding.tvOption5)
            add(binding.tvOption6)
        }
    }*/
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw java.lang.RuntimeException("FragmentGameBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        observeViewModel()
        //setClickListenersToOptions()
        activity = getActivity() as MainActivity?
        activity?.playSoundtrack(false)
        activity?.isMuted?.let { viewModel.setSoundtrack(it) }
    }

    /*private fun setClickListenersToOptions() {
        for (tvOption in tvOptions) {
            tvOption.setOnClickListener {
                viewModel.chooseAnswer(tvOption.text.toString().toInt())
            }
        }
    }*/

    private fun observeViewModel() {
//        viewModel.question.observe(viewLifecycleOwner) {
//            with(binding) {
//                //tvSum.text = it.sum.toString()
//                //tvLeftNumber.text = it.visibleNumber.toString()
//                for (i in 0 until tvOptions.size) {
//                    tvOptions[i].text = it.options[i].toString()
//                }
//                val rnd = Random
//                var color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
//                tvSum.backgroundTintList = ColorStateList.valueOf(color);
//                color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
//                tvLeftNumber.setBackgroundColor(color)
//                color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
//                tvQuestion.setBackgroundColor(color)
//            }
//        }
//        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner) {
//            binding.progressBar.setProgress(it, true)
//        }
//        viewModel.enoughPercent.observe(viewLifecycleOwner) {
//            val color = getColorByState(it)
//            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
//        }
//        viewModel.formattedTime.observe(viewLifecycleOwner) {
//            binding.tvTimer.text = it
//        }
//        viewModel.minPercent.observe(viewLifecycleOwner) {
//            binding.progressBar.secondaryProgress = it
//        }
//        viewModel.progressAnswers.observe(viewLifecycleOwner) {
//            binding.tvAnswersProgress.text = it
//        }
//        viewModel.enoughCount.observe(viewLifecycleOwner) {
//            binding.tvAnswersProgress.setTextColor(getColorByState(it))
//        }

        viewModel.countOfIncorrectAnswers.observe(viewLifecycleOwner) {
            activity?.setRedAlert()
        }
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun getColorByState(goodState: Boolean): Int {
//        val colorResId = if (goodState) {
//            android.R.color.holo_green_light
//        } else {
//            android.R.color.holo_red_light
//        }
//        return ContextCompat.getColor(requireContext(), colorResId)
//    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToGameFinishedFragment(
                gameResult
            )
        )
    }

//    private fun setRedAlert() {
//        val mColors = arrayOf(
//            ColorDrawable(
//                ContextCompat.getColor(
//                    requireContext(),
//                    android.R.color.holo_red_light
//                )
//            ), ColorDrawable(Color.WHITE)
//        )
//        val mTransition = TransitionDrawable(mColors)
//        binding.tvField.background = mTransition
//        mTransition.startTransition(500)
//    }
}