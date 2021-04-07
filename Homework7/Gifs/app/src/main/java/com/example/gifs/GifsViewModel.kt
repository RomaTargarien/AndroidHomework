package com.example.gifs

import android.app.Application
import androidx.lifecycle.*
import com.example.gifs.api.GifItem
import retrofit2.http.Query

class GifsViewModel(private val app: Application) : AndroidViewModel(app) {
    val trendGifsLiveData: LiveData<List<GifItem>>
    val gifsSearchLiveData: LiveData<List<GifItem>>
    private val giphyFetcher = GiphyFetcher()
    private val mutableSearchItem = MutableLiveData<String>()
    val searchItem: String
        get() = mutableSearchItem.value ?: ""
    init {
        trendGifsLiveData = GiphyFetcher().fetchTrendGifs()
        mutableSearchItem.value = QueryPreferences.getSoredQuerry(app)
        gifsSearchLiveData = Transformations.switchMap(mutableSearchItem){
            searchItem -> giphyFetcher.searchGifs(searchItem)
        }
    }
    fun fetchSearchedGifs(query: String = ""){
        QueryPreferences.setStoredQuerry(app,query)
        mutableSearchItem.value = query
    }
}