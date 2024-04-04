package dev.jayson.temperapp.data.model


import com.google.gson.annotations.SerializedName

data class LockedIconModel(
    @SerializedName("description")
    val description: String,
    @SerializedName("file")
    val fileModel: FileModel?,
    @SerializedName("title")
    val title: String
)