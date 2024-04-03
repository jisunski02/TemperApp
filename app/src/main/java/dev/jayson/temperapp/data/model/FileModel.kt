package dev.jayson.temperapp.data.model


import com.google.gson.annotations.SerializedName

data class FileModel(
    @SerializedName("contentType")
    val contentType: String,
    @SerializedName("details")
    val detailsModel: DetailsModel,
    @SerializedName("fileName")
    val fileName: String,
    @SerializedName("url")
    val url: String
)