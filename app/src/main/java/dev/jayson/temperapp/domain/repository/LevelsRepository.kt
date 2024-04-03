package dev.jayson.temperapp.domain.repository

import dev.jayson.temperapp.data.model.LevelListModel

interface LevelsRepository {
    suspend fun fetchLevelList(): LevelListModel
}