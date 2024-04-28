package com.example.musicplayer_app.Adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicplayer_app.MyExoPlayer
import com.example.musicplayer_app.PlayerActivity
import com.example.musicplayer_app.databinding.FavoriteSongListBinding
import com.example.musicplayer_app.databinding.SongListRecyclerRowBinding
import com.example.musicplayer_app.model.SongModel
import com.google.firebase.firestore.FirebaseFirestore

var TAG="FavoriteSongAdapter"
class FavoriteSongAdapter(private var songList: List<SongModel>): RecyclerView.Adapter<FavoriteSongAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: FavoriteSongListBinding):RecyclerView.ViewHolder(binding.root){

        fun onbind(songList: SongModel){

            FirebaseFirestore.getInstance().collection("Songs")
                .document(songList.id).get()
                .addOnSuccessListener {
                    val song= it.toObject(SongModel::class.java)
                    song?.apply {
                        binding.favTitleTxt.text = title
                        binding.favSubtitleTxt.text= subtitle
                        Glide.with(binding.favImg).load(coverUrl)
                            .apply( RequestOptions().transform(RoundedCorners(10)))
                            .into(binding.favImg)

                        binding.root.setOnClickListener {
                            MyExoPlayer.startPlaying(binding.root.context,song)
                            it.context.startActivity(Intent(it.context,PlayerActivity::class.java))
                        }



                    }

                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding= FavoriteSongListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onbind(songList[position])

        holder.binding.deleteFavsong.setOnClickListener {
            val song =songList[position]
            deleteFavoriteSong(song)
        }
    }
    fun setData(songs: MutableList<SongModel>) {
        songList = songs
        notifyDataSetChanged()
    }

    private fun deleteFavoriteSong(song:SongModel)
    {
        song.id?.let{id->
            val songRef = FirebaseFirestore.getInstance().collection("Songs").document(id)
            songRef.update("isfavorite",false)
                .addOnSuccessListener {
                    val newlist = songList.toMutableList()
                    newlist.remove(song)
                    songList=newlist.toList()
                    notifyDataSetChanged()
                }
                .addOnFailureListener { e->
                    Log.e(TAG, "Failed to remove song from favorites: ${e.message}")
                }
        }
    }

}