package com.example.todolist

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProviders
import com.example.todolist.viewmodels.GoalDetailViewModel
import java.util.*

private const val ARG_GOAL_ID = "goal_id"

class GoalFragment : Fragment() {

    private lateinit var goal: Goal
    private lateinit var goal_name: EditText
    private lateinit var goal_description: EditText
    private lateinit var add_button: AppCompatButton
    private lateinit var goal_done: CheckBox
    private var callBackTolist: CallBackTolist? = null

    interface CallBackTolist{
        fun callback(date: Date)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBackTolist = context as CallBackTolist
    }


    private val goalDetailViewModel: GoalDetailViewModel by lazy {
        ViewModelProviders.of(this).get(GoalDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goal = Goal()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_goal, container, false)
        goal_name = view.findViewById(R.id.goal_name)
        goal_description = view.findViewById(R.id.goal_desription)
        add_button = view.findViewById(R.id.add_goal_to_list)
        goal_done = view.findViewById(R.id.goal_done)
        return view
    }

    override fun onStart() {
        super.onStart()
        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                goal.title = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        val descriptionWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                goal.description = s.toString()
            }

        }
        goal_name.addTextChangedListener(titleWatcher)
        goal_description.addTextChangedListener(descriptionWatcher)
        val goalID: UUID = arguments?.getSerializable(ARG_GOAL_ID) as UUID
        goalDetailViewModel.loadGoal(goalID)
        add_button.setOnClickListener {
            goalDetailViewModel.saveGoal(goal)
            callBackTolist?.callback(goal.date)
        }
        goal_done.apply {
            setOnCheckedChangeListener{_, isChecked -> goal.isDone = isChecked}
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(goalUi: UUID): GoalFragment {
            val args = Bundle().apply {
                putSerializable(ARG_GOAL_ID,goalUi)
            }
            return GoalFragment().apply {
                arguments = args
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goalDetailViewModel.goalLiveData.observe(
                viewLifecycleOwner, androidx.lifecycle.Observer { goal -> goal?.let {
            this.goal = goal
            updateUI()
        } }
        )
    }
    private fun updateUI(){
        goal_name.setText(goal.title)
        goal_description.setText(goal.description)
        goal_done.apply {
            isChecked = goal.isDone
            jumpDrawablesToCurrentState()
        }
    }
}