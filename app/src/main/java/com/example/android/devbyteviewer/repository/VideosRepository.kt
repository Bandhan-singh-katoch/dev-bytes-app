
package com.example.android.devbyteviewer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.devbyteviewer.database.VideosDatabase
import com.example.android.devbyteviewer.database.asDomainModel
import com.example.android.devbyteviewer.domain.DevByteVideo
import com.example.android.devbyteviewer.network.Network
import com.example.android.devbyteviewer.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// this repository fetches the video from the network and stores on database

class VideosRepository(private val database: VideosDatabase){

    val videos : LiveData<List<DevByteVideo>> = Transformations.map(database.videoDao.getVideos()){
        it.asDomainModel()
    }

    suspend fun refreshVideos(){
        withContext(Dispatchers.IO){
            val playlist = Network.devbytes.getPlaylist().await()
            database.videoDao.insertAll(playlist.asDatabaseModel())
        }
    }

}

