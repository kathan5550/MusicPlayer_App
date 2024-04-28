package com.example.musicplayer_app.model

data class ArtistModel(
    val About: String,
    val born: String,
    val genre: String,
    val hometown:String,
    val name: String,
    val coverUrl: String,
    val subtitle:String,
    var songs:List<String>
)
{
    constructor():this("","","","","","","", listOf())
}
