package com.example.musicplayer_app.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicplayer_app.ArtistPlaylist_Activity
import com.example.musicplayer_app.SongListActivity
import com.example.musicplayer_app.databinding.ActivityArtistPlaylistBinding
import com.example.musicplayer_app.databinding.ArtistPlaylistBinding
import com.example.musicplayer_app.databinding.CategoryItemRecyclerRowBinding
import com.example.musicplayer_app.model.ArtistModel

class ArtistAdapter(private val artistList: List<ArtistModel>):
    RecyclerView.Adapter<ArtistAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ArtistPlaylistBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun onbind(artistList:ArtistModel)
        {

            binding.ArtistTitleTxt.text=artistList.name
            binding.ArtistSubtitleTxt.text=artistList.subtitle
            Glide.with(binding.ArtistImg).load(artistList.coverUrl)
                .apply( RequestOptions().transform(RoundedCorners(32)))
                .into(binding.ArtistImg)

            val context = binding.root.context
            binding.root.setOnClickListener {
                ArtistPlaylist_Activity.artistList=artistList
                context.startActivity(Intent(context, ArtistPlaylist_Activity::class.java))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=
            ArtistPlaylistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return artistList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onbind(artistList[position])
    }
}