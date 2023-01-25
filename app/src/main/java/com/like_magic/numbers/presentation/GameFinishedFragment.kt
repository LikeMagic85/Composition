package com.like_magic.numbers.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.like_magic.numbers.R
import com.like_magic.numbers.databinding.FragmentGameFinishedBinding
import com.like_magic.numbers.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private lateinit var gameResult: GameResult
    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        renderData(gameResult)
    }

    private fun renderData(gameResult: GameResult){
        if (gameResult.winner){
            binding.emojiResult.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_smile))
        }else {
            binding.emojiResult.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_sad))
        }
        with(binding){
            tvRequiredAnswers.text = String.format(resources.getString(R.string.required_score), gameResult.gameSettings.minCountOfRightAnswers)
            tvRequiredPercentage.text = String.format(resources.getString(R.string.required_percentage), gameResult.gameSettings.minPercentOgRightAnswers)
            tvScoreAnswers.text= String.format(resources.getString(R.string.score_answers), gameResult.countOfRightAnswers)
            tvScorePercentage.text =String.format(resources.getString(R.string.score_percentage), getPercentOfRightAnswer(gameResult))
        }
    }

    private fun getPercentOfRightAnswer(gameResult: GameResult):Int{
        return if (gameResult.countOfQuestions == 0){
            0
        }else {
            ((gameResult.countOfRightAnswers / gameResult.countOfQuestions.toDouble())*100).toInt()
        }
    }

    private fun setClickListeners() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    retryGame()
                }
            })
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    @Suppress("DEPRECATION")
    private fun parseArgs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(
                KEY_GAME_RESULT,
                GameResult::class.java
            )?.let {
                gameResult = it
            }
        } else {
            requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
                gameResult = it
            }

        }
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(
            GameFragment.NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val KEY_GAME_RESULT = "result"
        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }
}
