package com.example.musicplayer_app.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicplayer_app.databinding.AdvertisementScotiabankBinding
import com.example.musicplayer_app.model.AdvertisementModel

class AdvertisementAdapter(private val AdvList:List<AdvertisementModel>):RecyclerView.Adapter<AdvertisementAdapter.myViewholder>() {

    class myViewholder(val binding: AdvertisementScotiabankBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun onbind(AdvList:AdvertisementModel)
        {
            binding.advTxt.text=AdvList.link
            Glide.with(binding.gifImage).load(AdvList.coverUrl)
                .into(binding.gifImage)

           binding.advLayout.setOnClickListener {
               openAdvertisementLink(binding.root.context,AdvList.linkUrl)
            }
        }
        fun openAdvertisementLink(context: Context,linkUrl:String){
            val intent:Intent

            if(linkUrl.startsWith("http://")||linkUrl.startsWith("https://"))
            {
                intent = Intent(Intent.ACTION_VIEW,Uri.parse(linkUrl))
            }
            else{
                val launchintent= context.packageManager.getLaunchIntentForPackage(linkUrl)
                if (launchintent != null)
                {
                    intent = launchintent
                }
                else
                {
                    intent=Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=$linkUrl"))
                }
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewholder {
       val binding = AdvertisementScotiabankBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       return myViewholder(binding)
    }

    override fun getItemCount(): Int {
        return AdvList.size
    }

    override fun onBindViewHolder(holder: myViewholder, position: Int) {
      holder.onbind(AdvList[position])
    }



}