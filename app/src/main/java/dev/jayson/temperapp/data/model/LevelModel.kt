package dev.jayson.temperapp.data.model


import com.google.gson.annotations.SerializedName

data class LevelModel(
    @SerializedName("activities")
    val activities: List<ActivityModel>?,
    @SerializedName("description")
    val description: String,
    @SerializedName("level")
    val level: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("title")
    val title: String
)