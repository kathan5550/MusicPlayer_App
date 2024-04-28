package com.example.musicplayer_app.model

data class MadeForYouModel(
    val title:String,
    val coverUrl:String,
    val subtitle:String,
    val songs:List<String>
)
{
    constructor():this("","","", listOf())
}
