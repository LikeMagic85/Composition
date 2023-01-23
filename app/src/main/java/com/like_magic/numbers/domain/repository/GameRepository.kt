package com.like_magic.numbers.domain.repository

import com.like_magic.numbers.domain.entity.GameSettings
import com.like_magic.numbers.domain.entity.Level
import com.like_magic.numbers.domain.entity.Question

interface GameRepository {

    fun getQuestion(maxSumValue:Int, countOfOptions:Int):Question

    fun getGameSettings(level: Level):GameSettings
}