package com.like_magic.numbers.presentation

import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.like_magic.numbers.R
import com.like_magic.numbers.domain.entity.GameResult

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count:Int){
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoredAnswers")
fun bindScoreAnswers(textView: TextView, count:Int){
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("requiredPercent")
fun bindRequiredPercent(textView: TextView, count:Int){
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        count
    )
}

@BindingAdapter("scoredPercent")
fun bindScoreAPercent(textView: TextView, gameResult: GameResult){
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswer(gameResult)
    )
}

@BindingAdapter("emoji")
fun bindEmoji(imageView: ImageView, winner:Boolean){
    if (winner) {
        imageView.setImageDrawable(
            ContextCompat.getDrawable(
                imageView.context,
                R.drawable.ic_smile
            )
        )
    } else {
        imageView.setImageDrawable(
            ContextCompat.getDrawable(
                imageView.context,
                R.drawable.ic_sad
            )
        )
    }
}

private fun getPercentOfRightAnswer(gameResult: GameResult): Int {
    return if (gameResult.countOfQuestions == 0) {
        0
    } else {
        ((gameResult.countOfRightAnswers / gameResult.countOfQuestions.toDouble()) * 100).toInt()
    }
}

@BindingAdapter("numberAsText")
fun bindSum(textView: TextView, number:Int){
    textView.text = number.toString()
}

@BindingAdapter("progress")
fun bindProgress(progressBar: ProgressBar, number:Int){
    progressBar.setProgress(number, true)
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, state:Boolean){
    textView.setTextColor(
        ContextCompat.getColor(
            textView.context,
            getColorByState(state)
        )
    )
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, state:Boolean){
    progressBar.progressTintList = ColorStateList.valueOf(
        ContextCompat.getColor(
            progressBar.context,
            getColorByState(state)
        )
    )
}

@BindingAdapter("onOptionClickListener")
fun setClickListener(textView: TextView, clickListener : OnOptionsClickListener){
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}

private fun getColorByState(state: Boolean): Int {
    val color = if (state) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return color
}

interface OnOptionsClickListener {
    fun onOptionClick(option : Int)
}