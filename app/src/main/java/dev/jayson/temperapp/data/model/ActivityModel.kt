package dev.jayson.temperapp.data.model


import com.google.gson.annotations.SerializedName

data class ActivityModel(
    @SerializedName("challengeId")
    val challengeId: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("descriptionB")
    val descriptionB: Any?,
    @SerializedName("icon")
    val iconModel: IconModel?,
    @SerializedName("id")
    val id: String,
    @SerializedName("lockedIcon")
    val lockedIconModel: LockedIconModel?,
    @SerializedName("state")
    val state: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("titleB")
    val titleB: String?,
    @SerializedName("type")
    val type: String
)