package com.example.musicplayer_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.musicplayer_app.Adapter.MadeForYouAdapter
import com.example.musicplayer_app.Adapter.SongListAdapter
import com.example.musicplayer_app.databinding.ActivityMadeForYouBinding
import com.example.musicplayer_app.model.MadeForYouModel

class MadeForYou_Activity : AppCompatActivity() {

    companion object{
        lateinit var MadeforyouList:MadeForYouModel
    }

    lateinit var songListAdapter: SongListAdapter
    lateinit var binding: ActivityMadeForYouBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMadeForYouBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gifTitle.text= MadeforyouList.title
        binding.gifSubtitle.text= MadeforyouList.subtitle

        Glide.with(binding.gifBackimg).load(MadeforyouList.coverUrl)
            .into(binding.gifBackimg)

        setMadeforYouRecyclerView()

    }
    fun setMadeforYouRecyclerView()
    {
        songListAdapter= SongListAdapter(MadeforyouList.songs)
        binding.MFUDetailRecyclerview.layoutManager=LinearLayoutManager(this)
        binding.MFUDetailRecyclerview.adapter=songListAdapter

    }
}