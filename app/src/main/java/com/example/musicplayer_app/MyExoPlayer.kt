package com.example.musicplayer_app

import android.content.Context
import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.musicplayer_app.model.SongModel
import com.google.android.material.tabs.TabLayout.TabGravity
import com.google.firebase.firestore.FirebaseFirestore

object MyExoPlayer {

    private var exoPlayer: ExoPlayer? = null
    private var currentsong: SongModel? = null
    val TAG="MyExoPlayer"
    fun getInstance(): ExoPlayer? {
        return exoPlayer
    }

    fun getcurrentSong(): SongModel? {
        return currentsong
    }

    fun startPlaying(context: Context, song: SongModel) {
        if (exoPlayer == null) {
            exoPlayer = ExoPlayer.Builder(context).build()
        }

        if (currentsong != song) {
            currentsong = song
            UpdateCount()
            currentsong?.url?.apply {
                val mediaItem = MediaItem.fromUri(this)
                exoPlayer?.setMediaItem(mediaItem)
                exoPlayer?.prepare()
                exoPlayer?.play()
            }
        }
    }


    fun UpdateCount()
    {
        currentsong?.id?.let { id ->
            FirebaseFirestore.getInstance().collection("Songs")
                .document(id)
                .get().addOnSuccessListener{
                var latestCount = it.getLong("count")
                if (latestCount == null) {
                    latestCount = 1L
                } else {
                    latestCount = latestCount + 1
                }
                FirebaseFirestore.getInstance().collection("Songs")
                    .document(id)
                    .update(mapOf("count" to latestCount))
            }
        }


    }

    fun addToFavorite(context: Context,song:SongModel)
    {
        song.id?.let{ id->
            val songRef =FirebaseFirestore.getInstance().collection("Songs").document(id)
            songRef.update("isfavorite",true)
                .addOnSuccessListener {
                    Log.d(TAG,"song add to Favorite:${song.title}")
                }
                .addOnFailureListener { e->
                    Log.e(TAG,"song Not add to Favorite:${e.message}")
                }
        }
    }
}











