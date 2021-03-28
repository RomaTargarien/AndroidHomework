package com.example.todolist

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.adapters.DateAdapter
import com.example.todolist.adapters.ToDoListAdapter
import com.example.todolist.decorators.SpaceItemHorizontalDecoration
import com.example.todolist.decorators.SpacesItemVerticalDecoration
import com.example.todolist.viewmodels.GoalListViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

private const val ARG_DATE = "date"

class ToDoListFragment : Fragment() {

    interface Callbacks{
        fun onGoalSelected(goalUI: UUID)
    }
    private var callbacks: Callbacks? = null
    private lateinit var goals_recyclerView: RecyclerView
    private lateinit var dates_recyclerView: RecyclerView
    private var adapter: ToDoListAdapter? = null
    private lateinit var add_button: FloatingActionButton
    private var date = Date()
    private val paint = Paint()
    private val goalListViewModel: GoalListViewModel by lazy {
        ViewModelProviders.of(this).get(GoalListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_to_do_list, container, false)
        goals_recyclerView = view.findViewById(R.id.toDoList_recyclyclerView)
        dates_recyclerView = view.findViewById(R.id.dates_recyclerView)
        goals_recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        dates_recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        goals_recyclerView.addItemDecoration(SpacesItemVerticalDecoration(35))
        dates_recyclerView.addItemDecoration(SpaceItemHorizontalDecoration(10))
        goals_recyclerView.adapter = adapter
        dates_recyclerView.adapter = DateAdapter(createDates()){
            date = it
            goalListViewModel.getGoalsListLiveData(it).observe(viewLifecycleOwner,androidx.lifecycle.Observer {
                    goals -> goals?.let {
                udpateUI(goals)
            }
            })
        }
        add_button = view.findViewById(R.id.add)
        add_button.setOnClickListener {
            val goal = Goal()
            goal.date = date
            goalListViewModel.addGoal(goal)
            callbacks?.onGoalSelected(goal.id)
        }
        if (arguments?.getSerializable(ARG_DATE) != null){
            date = arguments?.getSerializable(ARG_DATE) as Date
        }
        goalListViewModel.getGoalsListLiveData(date).observe(viewLifecycleOwner,androidx.lifecycle.Observer {
            goals -> goals?.let {
            udpateUI(goals)
        }
        })
        if (adapter != null){
            adapter!!.notifyDataSetChanged()
        }
        initSwipe()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goalListViewModel.getGoalsListLiveData(date).observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            goals -> goals?.let {
            udpateUI(goals)
        }
        })
    }

    companion object {
        fun newInstance(date: Date): ToDoListFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE,date)
            }
            return ToDoListFragment().apply {
                arguments = args
            }
        }
    }

    private fun udpateUI(goals: MutableList<Goal>){
        adapter = ToDoListAdapter(goals) {
            callbacks?.onGoalSelected(it.id)
        }
        goals_recyclerView.adapter = adapter
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createDates(): List<LocalDate> {
        val date = Date()
        val listDate = mutableListOf<LocalDate>()
        val apiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val timestampAsDateString = DateTimeFormatter.ISO_INSTANT
                .format(Instant.ofEpochSecond(date.time/1000))
        var localdate = LocalDate.parse(timestampAsDateString,apiFormat)
        val period = Period.of(0,0,1)
        listDate.add(localdate)
        for (i in 0..365){
            localdate = localdate.plus(period)
            listDate.add(localdate)
        }
        return listDate
    }

    fun initSwipe() {
        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT ) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                if (swipeDir == ItemTouchHelper.LEFT){
                    adapter?.removeItem(position)
                }
            }
            @SuppressLint("UseCompatLoadingForDrawables")
            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
                    val itemView = viewHolder.itemView
                    val height = itemView.bottom - itemView.top
                    val wigth = height/3
                    if (dX < 0 ){
                        paint.setColor(Color.parseColor("#D32F2F"))
                        val background = RectF(itemView.right.toFloat() + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
                        c.drawRect(background,paint)
                        val d = resources.getDrawable(R.drawable.ic_baseline_delete_forever_24)
                        val icon_dest = RectF(itemView.right.toFloat() - 2 * wigth, itemView.top.toFloat() + wigth, itemView.right.toFloat() - wigth, itemView.bottom.toFloat() - wigth)
                        c.drawBitmap(drawableToBitmap(d),null,icon_dest,paint)
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
            fun drawableToBitmap(drawable: Drawable): Bitmap {

                if (drawable is BitmapDrawable) {
                    return (drawable as BitmapDrawable).getBitmap();
                }

                val bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                val canvas = Canvas(bitmap);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);
                return bitmap;
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(goals_recyclerView)
    }
}