package ru.artempugachev.remote.model

import com.google.gson.annotations.SerializedName

class OwnerModel(@SerializedName("login") val ownerName: String,
                 @SerializedName("avatarUrl") val ownerAvatar: String)