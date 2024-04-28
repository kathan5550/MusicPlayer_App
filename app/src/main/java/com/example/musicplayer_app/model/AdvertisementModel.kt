package com.example.musicplayer_app.model

data class AdvertisementModel(
    val coverUrl:String,
    val link:String,
    val linkUrl:String,
)
{
    constructor():this("","","")
}
