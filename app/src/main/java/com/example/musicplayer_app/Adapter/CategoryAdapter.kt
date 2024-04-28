package com.example.musicplayer_app.Adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicplayer_app.SongListActivity
import com.example.musicplayer_app.databinding.CategoryItemRecyclerRowBinding
import com.example.musicplayer_app.model.CategoryModel

class CategoryAdapter(private val categoryList:List<CategoryModel>):RecyclerView.Adapter<CategoryAdapter.myViewHolder> (){

    class myViewHolder(private val binding: CategoryItemRecyclerRowBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun onbind(categoryList:CategoryModel)
        {
            binding.cattxt.text=categoryList.name
            Glide.with(binding.coverImageView).load(categoryList.coverUrl)
                .apply( RequestOptions().transform(RoundedCorners(32)))
                .into(binding.coverImageView)

            val context = binding.root.context
            binding.root.setOnClickListener{
                SongListActivity.categoryList=categoryList
                context.startActivity(Intent(context,SongListActivity::class.java))


            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val binding=CategoryItemRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return categoryList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
            holder.onbind(categoryList[position])
    }
}