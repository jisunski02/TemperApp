package dev.jayson.temperapp.data

import android.content.Context
import com.google.gson.Gson
import dev.jayson.temperapp.data.model.LevelListModel
import dev.jayson.temperapp.domain.repository.LevelsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LevelsRepositoryImpl(private val context: Context) : LevelsRepository {

    override suspend fun fetchLevelList(): LevelListModel {
        return withContext(Dispatchers.IO) {
            val jsonString = context.assets.open("notion.json").bufferedReader().use { it.readText() }
            Gson().fromJson(jsonString, LevelListModel::class.java)
        }
    }
}