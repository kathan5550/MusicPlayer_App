package com.example.musicplayer_app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicplayer_app.Adapter.SongListAdapter
import com.example.musicplayer_app.databinding.ActivityArtistPlaylistBinding
import com.example.musicplayer_app.databinding.ActivitySiginBinding
import com.example.musicplayer_app.model.ArtistModel
import com.example.musicplayer_app.model.SongModel
import com.google.firebase.firestore.FirebaseFirestore

class ArtistPlaylist_Activity : AppCompatActivity() {


    companion object{
        lateinit var artistList:ArtistModel
    }

    lateinit var songListAdapter: SongListAdapter
    lateinit var binding: ActivityArtistPlaylistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityArtistPlaylistBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.artistName.text= artistList.name
        binding.artistAbout.text=artistList.About
        binding.artistHometown.text=artistList.hometown
        binding.artistBorn.text=artistList.born
        binding.artistGener.text=artistList.genre

        Glide.with(binding.artistImg).load(artistList.coverUrl)
            .into(binding.artistImg)

        setupSongListRecyclerView()





    }
    fun setupSongListRecyclerView()
    {
        songListAdapter = SongListAdapter(artistList.songs)
        binding.artistRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.artistRecyclerview.adapter=songListAdapter

    }

}