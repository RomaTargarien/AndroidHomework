package com.example.post.utils

import com.example.post.R
import java.io.Serializable


val PostS_LIST by lazy {
    listOf<Post>(
        Post(
            R.drawable.brad_pitt1,
            93829,
            comments
        ),
        Post(
            R.drawable.brad_pitt2,
            2342
        ),
        Post(
            R.drawable.brad_pitt3,
            55435
        ),
        Post(
            R.drawable.brad_pitt4,
            57594
        ),
        Post(
            R.drawable.brad_pitt5,
            42379
        ),
        Post(
            R.drawable.brad_pitt6,
            933
        ),
        Post(
            R.drawable.brad_pitt1,
            238
        ),
        Post(
            R.drawable.brad_pitt2,
            49238
        ),
        Post(
            R.drawable.brad_pitt3,
            3928
        ),
        Post(
            R.drawable.brad_pitt4,
            1282
        ),
        Post(
            R.drawable.brad_pitt5,
            2
        ),
        Post(
            R.drawable.brad_pitt6,
            324
        )
    )
}

data class Post(
    val image: Int,
    val amountOfLikes: Int,
    val comments: List<Comment>? = null): Serializable {
}

val comments: List<Comment>  = listOf(
    Comment(
        "Wow! Nice look",
        "Ann Hettaway",
        R.drawable.djoli
    ),
    Comment(
        "Great!",
        "Kolin Farell",
        R.drawable.farell
    ),
    Comment(
        "Much Better!",
        "Tom Krus",
        R.drawable.kruz
    ),
    Comment(
        "I look better",
        "Rock",
        R.drawable.scala
    )
)