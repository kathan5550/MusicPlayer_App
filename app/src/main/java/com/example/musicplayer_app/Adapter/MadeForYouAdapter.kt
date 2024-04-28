package com.example.musicplayer_app.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicplayer_app.ArtistPlaylist_Activity
import com.example.musicplayer_app.MadeForYou_Activity
import com.example.musicplayer_app.databinding.BackgroundGifBinding
import com.example.musicplayer_app.model.MadeForYouModel

class MadeForYouAdapter(private val MadeforyouList:List<MadeForYouModel>):RecyclerView.Adapter<MadeForYouAdapter.myViewHolder>() {

    class myViewHolder(val binding: BackgroundGifBinding):RecyclerView.ViewHolder(binding.root)
    {

        fun bind(MadeforyouList:MadeForYouModel){

            binding.gifTitle.text=MadeforyouList.title
            Glide.with(binding.gifImage).load(MadeforyouList.coverUrl)
                .into(binding.gifImage)


            val context = binding.root.context
            binding.root.setOnClickListener {
                MadeForYou_Activity.MadeforyouList=MadeforyouList
                context.startActivity(Intent(context, MadeForYou_Activity::class.java))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val binding=BackgroundGifBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return MadeforyouList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
       holder.bind(MadeforyouList[position])
    }
}