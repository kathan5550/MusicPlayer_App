package com.example.musicplayer_app.model

data class CategoryModel(
    val name:String,
    val coverUrl:String,
    val headline:String,
    var songs:List<String>
){
    constructor():this("","", "",listOf())
}
