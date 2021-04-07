package com.example.gifs

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.os.Environment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.example.gifs.decorators.SpacesItemVerticalDecoration
import com.romainpiel.shimmer.Shimmer
import com.romainpiel.shimmer.ShimmerTextView
import java.io.*


private const val REQEST_GIF = 0
private const val DIALOG_GIF = "Dialog date"

class GifsListFragment : Fragment() {

    private lateinit var gifsRecyclerView: RecyclerView
    private lateinit var trendingGifsRecyclerView: RecyclerView
    private lateinit var gifsViewModel: GifsViewModel
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: AppCompatButton
    private lateinit var trendsTextView: TextView
    private lateinit var giphyTextView: ShimmerTextView
    private lateinit var seeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gifsViewModel = ViewModelProviders.of(this).get(GifsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gifs_list, container, false)
        initViews(view)
        initRecyclers()
        val shader = LinearGradient(0f,0f,60f,60f,
                intArrayOf(Color.parseColor("#9C27B0"),Color.parseColor("#E85D8C")),
                floatArrayOf(0f,8f),Shader.TileMode.MIRROR)
        trendsTextView.paint.shader = shader
        seeTextView.paint.shader = shader
        val anim: Animation = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 300
        anim.startOffset = 20
        anim.repeatMode
        anim.setRepeatCount(Animation.INFINITE)
        trendsTextView.startAnimation(anim)
        val shimer = Shimmer()
        shimer.start(giphyTextView)
        return view
    }

    override fun onStart() {
        super.onStart()
        var serchText = ""
        searchEditText.setText(gifsViewModel.searchItem)
        val searchWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                serchText = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        searchEditText.addTextChangedListener(searchWatcher)
        searchButton.setOnClickListener {
            gifsViewModel.fetchSearchedGifs(serchText)
        }
    }

    private fun initViews(view: View){
        gifsRecyclerView = view.findViewById(R.id.gifs_recycler_view)
        trendingGifsRecyclerView = view.findViewById(R.id.trends_recycler_view)
        searchEditText = view.findViewById(R.id.search_edit_text)
        searchButton = view.findViewById(R.id.search_button)
        trendsTextView = view.findViewById(R.id.trends_textView)
        giphyTextView = view.findViewById(R.id.giphy_textView)
        seeTextView = view.findViewById(R.id.see_textView)
    }

    private fun initRecyclers(){
        gifsRecyclerView.addItemDecoration(SpacesItemVerticalDecoration(11))
        gifsRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        gifsViewModel.gifsSearchLiveData.observe(this, Observer {
            gifItems -> gifsRecyclerView.adapter = GifsAdapter(gifItems){
            GifDialogFragment.newInsctance(it).apply {
                setTargetFragment(this@GifsListFragment, REQEST_GIF)
                show(this@GifsListFragment.requireFragmentManager(), DIALOG_GIF)
            }
        }
        })
        trendingGifsRecyclerView.addItemDecoration(SpacesItemVerticalDecoration(11))
        trendingGifsRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        gifsViewModel.trendGifsLiveData.observe(this, Observer {
            trendinfGifs -> trendingGifsRecyclerView.adapter = GifsAdapter(trendinfGifs){
            GifDialogFragment.newInsctance(it).apply {
                setTargetFragment(this@GifsListFragment, REQEST_GIF)
                show(this@GifsListFragment.requireFragmentManager(), DIALOG_GIF)
            }
        }
        })
    }
}


