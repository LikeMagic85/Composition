package com.like_magic.numbers.domain.entity

data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOgRightAnswers: Int,
    val gameTimeInSeconds: Int
)