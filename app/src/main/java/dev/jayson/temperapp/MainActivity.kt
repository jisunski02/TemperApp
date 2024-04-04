package dev.jayson.temperapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import dev.jayson.temperapp.databinding.ActivityMainBinding
import dev.jayson.temperapp.presentation.adapter.LevelsAdapter
import dev.jayson.temperapp.presentation.viewmodel.LevelsViewModel
import dev.jayson.temperapp.presentation.viewmodel.LevelsViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: LevelsViewModel
    private lateinit var levelsAdapter: LevelsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Initialize the ViewModel
        val factory = LevelsViewModelFactory(applicationContext)
        viewModel = ViewModelProvider(this, factory)[LevelsViewModel::class.java]


        setupTabhostDays()
        setupLevelsRecyclerView()

    }

    private fun setupLevelsRecyclerView() {
        levelsAdapter = LevelsAdapter(listOf()) // Initialize with an empty list
        binding.rvLevels.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = levelsAdapter
        }

        viewModel.levels.observe(this, Observer { levelModelList ->
            // Update UI with levels data
            // levelModelList is the object structure matching notion.json on the top level structure
            levelsAdapter.updateLevels(levelModelList.levelModels)
            levelModelList.levelModels.forEach { levels ->
                Log.e("levelsHere", levels.title)

                //val firstLevel = levelModelList.levelModels[0]

                levels.activities?.forEach{ activities ->
                    Log.e("activitiesHere", activities.title)
                }
            }


        })
    }

    private fun setupTabhostDays(){
        val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

        for (day in days) {
            val tab = binding.tabLayout.newTab()
            tab.text = day
            binding.tabLayout.addTab(tab)
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            @SuppressLint("MissingInflatedId")
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    val selectedDay = days[it.position]
                    val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.fragment_day, binding.container, false)
                    view.findViewById<TextView>(R.id.textView).text = "$selectedDay Content"
                    binding.container.removeAllViews()
                    binding.container.addView(view)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
    }

