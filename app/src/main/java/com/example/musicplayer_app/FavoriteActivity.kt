package com.example.musicplayer_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicplayer_app.Adapter.FavoriteSongAdapter
import com.example.musicplayer_app.databinding.ActivityFavoriteBinding
import com.example.musicplayer_app.model.SongModel
import com.google.firebase.firestore.FirebaseFirestore

class FavoriteActivity : AppCompatActivity() {

    private lateinit var favoriteSongAdapter: FavoriteSongAdapter
    lateinit var binding:ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)



        setupRecyclerView()
        loadFavoriteSongs()

    }

    private fun setupRecyclerView() {
        binding.favoriteRecyclerView.layoutManager = GridLayoutManager(this,2)
        favoriteSongAdapter = FavoriteSongAdapter(emptyList())
        binding.favoriteRecyclerView.adapter = favoriteSongAdapter
    }

    private fun loadFavoriteSongs() {
        // Query Firestore for favorite songs and populate the adapter
        FirebaseFirestore.getInstance().collection("Songs")
            .whereEqualTo("isfavorite", true)
            .get()
            .addOnSuccessListener { documents ->
                val favoriteSongsList = mutableListOf<SongModel>()
                for (document in documents) {
                    val song = document.toObject(SongModel::class.java)
                    favoriteSongsList.add(song)
                }
                favoriteSongAdapter.setData(favoriteSongsList)
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error getting favorite songs", exception)
            }
    }

    companion object {
        private const val TAG = "FavoriteActivity"
    }

}