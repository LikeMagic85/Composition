package com.like_magic.numbers.domain.usecases

import com.like_magic.numbers.domain.entity.GameSettings
import com.like_magic.numbers.domain.entity.Level
import com.like_magic.numbers.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level):GameSettings{
        return repository.getGameSettings(level)
    }
}