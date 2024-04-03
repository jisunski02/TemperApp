package dev.jayson.temperapp.data.model


import com.google.gson.annotations.SerializedName

data class LevelListModel(
    @SerializedName("levels")
    val levelModels: List<LevelModel>
)