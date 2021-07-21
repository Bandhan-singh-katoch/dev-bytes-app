
package com.example.android.devbyteviewer.repository

import com.example.android.devbyteviewer.database.VideosDatabase
import com.example.android.devbyteviewer.network.Network
import com.example.android.devbyteviewer.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// this repository fetches the video from the network and stores on database

class VideosRepository(private val database: VideosDatabase){

    suspend fun refreshVideos(){
        withContext(Dispatchers.IO){
            val playlist = Network.devbytes.getPlaylist().await()
            database.videoDao.insertAll(playlist.asDatabaseModel())
        }
    }

}

