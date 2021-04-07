package com.example.gifs.api

import com.google.gson.annotations.SerializedName


data class NestedJSONModel(var data: MutableList<Data>)

data class Data(var images: Images?)

data class Images(var original : GifItem,@SerializedName("downsized")var  size: Size)

data class Size(var height: Int, var width: Int)

data class GifItem(var url: String)