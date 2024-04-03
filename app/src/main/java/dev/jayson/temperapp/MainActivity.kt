package dev.jayson.temperapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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
}
