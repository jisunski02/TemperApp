package dev.jayson.temperapp.domain.usecase

import dev.jayson.temperapp.data.model.LevelListModel
import dev.jayson.temperapp.domain.repository.LevelsRepository

class LevelsUseCase(private val levelsRepository: LevelsRepository) {

    suspend fun invoke(): LevelListModel{
        return levelsRepository.fetchLevelList()
    }
}