package com.example.gifs.api



import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {
    @GET("trending?api_key=ve7RPYk6h9zOuZWDyubSLm8uUtHB9mo9&limit=50")
    suspend fun getTrendingGifs(): Response<NestedJSONModel>
    @GET("search?api_key=ve7RPYk6h9zOuZWDyubSLm8uUtHB9mo9")
    suspend fun getSearchedGifs(@Query("q") text: String,
                                @Query("limit") limit: String = "49"): Response<NestedJSONModel>
}