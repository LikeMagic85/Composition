package com.like_magic.numbers.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOgRightAnswers: Int,
    val gameTimeInSeconds: Int
): Parcelable