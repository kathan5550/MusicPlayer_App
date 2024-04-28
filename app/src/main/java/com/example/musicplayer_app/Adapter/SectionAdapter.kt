package com.example.musicplayer_app.Adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicplayer_app.MyExoPlayer
import com.example.musicplayer_app.PlayerActivity
import com.example.musicplayer_app.databinding.SongListRecyclerRowBinding
import com.example.musicplayer_app.databinding.TrenndingSectionRowBinding
import com.example.musicplayer_app.model.SongModel
import com.google.firebase.firestore.FirebaseFirestore

class SectionAdapter(private val songList: List<String>): RecyclerView.Adapter<SectionAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: TrenndingSectionRowBinding):RecyclerView.ViewHolder(binding.root){

        fun onbind(songList:String){

            FirebaseFirestore.getInstance().collection("Songs")
                .document(songList).get()
                .addOnSuccessListener {
                    val song= it.toObject(SongModel::class.java)
                    song?.apply {
                        binding.trendingTitleTxt.text = title
                        binding.trendingSubtitleTxt.text= subtitle
                        Glide.with(binding.trendingSectionImg).load(coverUrl)
                            .apply( RequestOptions().transform(RoundedCorners(20)))
                            .into(binding.trendingSectionImg)

                        binding.root.setOnClickListener {
                            MyExoPlayer.startPlaying(binding.root.context,song)
                            it.context.startActivity(Intent(it.context,PlayerActivity::class.java))
                        }
                    }

                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding= TrenndingSectionRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onbind(songList[position])
    }
}