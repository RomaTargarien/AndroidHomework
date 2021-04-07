package com.example.gifs

import android.app.DownloadManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gifs.api.Data
import com.example.gifs.api.GifItem
import com.example.gifs.api.GiphyApi
import com.example.gifs.api.NestedJSONModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "GiphyFetchr"

class GiphyFetcher {
    private val giphyApi: GiphyApi
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.giphy.com/v1/gifs/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        giphyApi = retrofit.create(GiphyApi::class.java)
    }
    fun searchGifs(query: String): LiveData<List<GifItem>>{
        val responseLiveDataSearch: MutableLiveData<List<GifItem>> = MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            val response = giphyApi.getSearchedGifs(query)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    responseLiveDataSearch.value = returnListGifItems(response)
                }
                else {
                    Log.e(TAG,"Failed to fetch fotos")
                }
            }
        }
        return responseLiveDataSearch
    }

    fun fetchTrendGifs(): LiveData<List<GifItem>> {
        val responseLiveData: MutableLiveData<List<GifItem>> = MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            val response = giphyApi.getTrendingGifs()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    responseLiveData.value = returnListGifItems(response)
                }
                else {
                    Log.e(TAG,"Failed to fetch fotos")
                }
            }
        }
        return responseLiveData
    }

    private fun returnListGifItems(response: Response<NestedJSONModel>): MutableList<GifItem> {
        val itemsList: MutableList<GifItem> = mutableListOf()
        var items:List<Data> = response.body()?.data ?: mutableListOf()
        items = items.filterNot {
            it.images!!.original.url.isBlank()
            it.images!!.size.height != it.images!!.size.width
        }
        for (item in items){
            val gifItem = GifItem(item.images?.original?.url.toString())
            itemsList.add(gifItem)
        }
        return itemsList
    }
}