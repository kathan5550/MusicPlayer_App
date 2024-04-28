package com.example.musicplayer_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicplayer_app.Adapter.SongListAdapter
import com.example.musicplayer_app.databinding.ActivitySongListBinding
import com.example.musicplayer_app.model.CategoryModel

class SongListActivity : AppCompatActivity() {

    companion object{
        lateinit var categoryList:CategoryModel
    }
    lateinit var songListAdapter: SongListAdapter
    lateinit var binding: ActivitySongListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.songListText.text = categoryList.name
        binding.headlineTxt.text= categoryList.headline
        Glide.with(binding.songListImage).load(categoryList.coverUrl)
            .apply (RequestOptions().transform(RoundedCorners(32)) )
            .into(binding.songListImage)

        setupSongListRecyclerView()
    }

    fun setupSongListRecyclerView()
    {
        songListAdapter = SongListAdapter(categoryList.songs)
        binding.songListRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.songListRecyclerview.adapter=songListAdapter

    }
}