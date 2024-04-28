package com.example.musicplayer_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicplayer_app.Adapter.AdvertisementAdapter
import com.example.musicplayer_app.Adapter.ArtistAdapter
import com.example.musicplayer_app.Adapter.CategoryAdapter
import com.example.musicplayer_app.Adapter.MadeForYouAdapter
import com.example.musicplayer_app.Adapter.SectionAdapter
import com.example.musicplayer_app.SignUp.HomePage_Activity
import com.example.musicplayer_app.SignUp.Sigin_activity
import com.example.musicplayer_app.databinding.ActivityMainBinding
import com.example.musicplayer_app.model.AdvertisementModel
import com.example.musicplayer_app.model.ArtistModel
import com.example.musicplayer_app.model.CategoryModel
import com.example.musicplayer_app.model.MadeForYouModel
import com.example.musicplayer_app.model.SongModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var sectionAdapter: SectionAdapter
    lateinit var artistAdapter: ArtistAdapter
    lateinit var advertisementAdapter: AdvertisementAdapter
    lateinit var madeForYouAdapter: MadeForYouAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCategories()
        getAdvetisement()
        getArtists()
        getMFYsongs()
        setTrendingSection("Trending",binding.section1,binding.Trending,binding.trendingRecyclerview)
        setTrendingSection("Hot Tracks",binding.section2,binding.HotTracks,binding.hottracksRecyclerview)
        MostlyPlayedSection("Mostly_Played",binding.MostlyPlayed,binding.MostlyPlayedTxt,binding.MostlyPlayedRecyclerview)
       // setupArtistPlaylist("Drake",binding.playbyartist,binding.txtArtist,binding.artistRecyclerview)
        binding.sidemenu.setOnClickListener{
            popupoptionmenu()
        }
    }

    fun popupoptionmenu()
    {
            val popupMenu=PopupMenu(this,binding.sidemenu)
            val inflator = popupMenu.menuInflater

        inflator.inflate(R.menu.option_menu,popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.logout->{
                    logout()
                    true
                }
                R.id.fav_song->{
                    FavoriteSongs()
                    true
                }
                R.id.user_profile->{
                    Userprofile()
                    true
                }
                else->false

            }
            false
        }
    }

    fun logout()
    {
        MyExoPlayer.getInstance()?.release()
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this,Sigin_activity::class.java))
        finish()
    }

    fun Userprofile()
    {
        startActivity(Intent(this,UserProfileActivity::class.java))
    }
    fun FavoriteSongs()
    {
        startActivity(Intent(this,FavoriteActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        ShowPlayerView()
    }
    fun ShowPlayerView()
    {
        binding.NowplayingLayout.setOnClickListener {
            startActivity(Intent(this,PlayerActivity::class.java))
        }
        MyExoPlayer.getcurrentSong()?.let {
            binding.NowplayingLayout.visibility=View.VISIBLE
            binding.NowplayingTitle.text=it.title
            Glide.with(binding.NowplayingImg).load(it.coverUrl)
                .apply( RequestOptions().transform(RoundedCorners(32)))
                .into(binding.NowplayingImg)
        }?:run {
            binding.NowplayingLayout.visibility=View.GONE
        }
    }

    fun getCategories(){
        FirebaseFirestore.getInstance().collection("Category")
            .get().addOnSuccessListener {
                val categoryList = it.toObjects(CategoryModel::class.java)
                setupcategoryRecyclerview(categoryList)
            }
    }

    fun setupcategoryRecyclerview(categoryList:List<CategoryModel>)
    {
        categoryAdapter = CategoryAdapter(categoryList)
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.categoryRecyclerView.adapter = categoryAdapter
    }

    fun getArtists()
    {
       FirebaseFirestore.getInstance().collection("Artist")
           .get().addOnSuccessListener {
               val artistList = it.toObjects(ArtistModel::class.java)
               setupArtistPlaylist(artistList)
           }

    }
    fun setupArtistPlaylist(artistList:List<ArtistModel>)
    {
        artistAdapter = ArtistAdapter(artistList)
        binding.ArtistRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.ArtistRecyclerView.adapter= artistAdapter
    }

    fun getMFYsongs()
    {
        FirebaseFirestore.getInstance().collection("MadeForYou")
            .get().addOnSuccessListener {
                val MadeforyouList=it.toObjects(MadeForYouModel::class.java)
                setMadeforYou(MadeforyouList)
            }
    }

    fun setMadeforYou(MadeforyouList:List<MadeForYouModel>)
    {
        madeForYouAdapter=MadeForYouAdapter(MadeforyouList)
        binding.madeforyouRecyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.madeforyouRecyclerView.adapter=madeForYouAdapter
    }

    fun getAdvetisement()
    {

        FirebaseFirestore.getInstance().collection("Advertisement")
            .get().addOnSuccessListener {
                val AdvList = it.toObjects(AdvertisementModel::class.java)
                setAdvertisement(AdvList)
            }

    }

    fun setAdvertisement(AdvList:List<AdvertisementModel>)
    {
        advertisementAdapter= AdvertisementAdapter(AdvList)
        binding.advRecyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.advRecyclerView.adapter=advertisementAdapter

    }


    fun setTrendingSection(id:String,Mainlayout:RelativeLayout,TitleView:TextView,recyclerView: RecyclerView){

        FirebaseFirestore.getInstance().collection("Sections")
            .document(id)
            .get().addOnSuccessListener {
                val section = it.toObject(CategoryModel::class.java)
                section?.apply {
                    Mainlayout.visibility=View.VISIBLE
                    TitleView.text=name
                    recyclerView.layoutManager= LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                    recyclerView.adapter= SectionAdapter(songs)
                    Mainlayout.setOnClickListener {
                        SongListActivity.categoryList=section
                        startActivity(Intent(this@MainActivity,SongListActivity::class.java))

                    }
                }
            }
    }

    fun MostlyPlayedSection(id:String,Mainlayout:RelativeLayout,TitleView:TextView,recyclerView: RecyclerView){

        FirebaseFirestore.getInstance().collection("Sections")
            .document(id)
            .get().addOnSuccessListener {

                //get Mostly played songs
                FirebaseFirestore.getInstance().collection("Songs")
                    .orderBy("count",Query.Direction.DESCENDING)
                    .limit(10)
                    .get().addOnSuccessListener {songListSnapshot->
                      val songModelList=  songListSnapshot.toObjects<SongModel>()
                        val songIdList = songModelList.map {
                            it.id

                        }.toList()
                        val section = it.toObject(CategoryModel::class.java)
                        section?.apply {
                            section.songs = songIdList
                            Mainlayout.visibility=View.VISIBLE
                            TitleView.text=name
                            recyclerView.layoutManager= LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                            recyclerView.adapter= SectionAdapter(songs)
                            Mainlayout.setOnClickListener {
                                SongListActivity.categoryList=section
                                startActivity(Intent(this@MainActivity,SongListActivity::class.java))

                            }
                        }
                    }

            }
    }



}