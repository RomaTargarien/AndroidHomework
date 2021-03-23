package com.example.post.utils

import java.io.Serializable


data class Comment(val text_of_comment: String,val comment_autor: String, val Post_of_autor: Int ):Serializable {
}