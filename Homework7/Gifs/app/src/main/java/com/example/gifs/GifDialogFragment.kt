package com.example.gifs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

private const val ARG_URL = "url"
private const val SUBJECT = "subject"
class GifDialogFragment: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.gif_dialog_fragment,container,false)
        val url = arguments?.getString(ARG_URL)
        context?.let {  Glide
                .with(it)
                .load(url)
                .apply(RequestOptions()
                        .placeholder(R.drawable.placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(view.findViewById(R.id.share_gif))}
        view.findViewById<AppCompatButton>(R.id.share_button).setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "*/*"
            intent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT)
            intent.putExtra(Intent.EXTRA_TEXT, url)
            startActivity(intent)
        }
        return view
    }

    companion object {
        fun newInsctance(url: String): GifDialogFragment{
            val args = Bundle().apply {
                putString(ARG_URL,url)
            }
            return GifDialogFragment().apply {
                arguments = args
            }
        }
    }
}